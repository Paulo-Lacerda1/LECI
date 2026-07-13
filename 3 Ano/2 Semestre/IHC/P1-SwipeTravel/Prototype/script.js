const STORAGE_KEYS = {
  accounts: 'swipetravel.accounts',
  session: 'swipetravel.session'
};

const state = {
  screen: 'auth',
  tripsFilter: 'active',
  detailTab: 'votes',
  currentTripId: 1,
  suggestionIndexByTrip: {},
  createTripDuration: 4,
  authMode: 'login',
  authenticated: false,
  currentUser: null,
  trips: readJSON('swipetravel.trips', [])
};

const $ = (selector) => document.querySelector(selector);
const $$ = (selector) => [...document.querySelectorAll(selector)];

function safeListen(selector, event, fn) {
  const el = $(selector);
  if (el) el.addEventListener(event, fn);
}

function readJSON(key, fallback) {
  try {
    const raw = localStorage.getItem(key);
    return raw ? JSON.parse(raw) : fallback;
  } catch (error) {
    return fallback;
  }
}

function writeJSON(key, value) {
  localStorage.setItem(key, JSON.stringify(value));
}

function getAccounts() {
  return readJSON(STORAGE_KEYS.accounts, []);
}

function saveAccounts(accounts) {
  writeJSON(STORAGE_KEYS.accounts, accounts);
}

function saveSession() {
  if (!state.authenticated || !state.currentUser) {
    sessionStorage.removeItem(STORAGE_KEYS.session);
    return;
  }
  sessionStorage.setItem(STORAGE_KEYS.session, JSON.stringify({ email: state.currentUser.email }));
}

function loadSession() {
  const raw = sessionStorage.getItem(STORAGE_KEYS.session);
  const session = raw ? JSON.parse(raw) : null;
  const accounts = getAccounts();
  const matchedAccount = session
    ? accounts.find((account) => account.email.toLowerCase() === session.email.toLowerCase())
    : null;

  if (matchedAccount) {
    state.authenticated = true;
    state.currentUser = matchedAccount;
    state.screen = 'home';
    return;
  }
  state.authenticated = false;
  state.currentUser = null;
  state.screen = 'auth';
}

function seedDemoAccount() {
  let accounts = getAccounts();
  let changed = false;

  const demoUsers = [
    { name: 'João', email: 'joao@swipetravel.app', password: '123' },
    { name: 'Tomás', email: 'tomas@swipetravel.app', password: '123' },
    { name: 'Sofia', email: 'sofia@swipetravel.app', password: '123' },
    { name: 'Nuno', email: 'nuno@swipetravel.app', password: '123' }
  ];

  demoUsers.forEach(demoUser => {
    if (!accounts.some(acc => acc.email === demoUser.email)) {
      accounts.push(demoUser);
      changed = true;
    }
  });

  if (changed) {
    saveAccounts(accounts);
  }
}

function getCurrentTrip() {
  return state.trips.find((trip) => trip.id === state.currentTripId) || state.trips[0];
}

function formatCurrency(value) {
  return `EUR${Number(value).toFixed(2).replace('.00', '')}`;
}

function initials(name) {
  return name.split(' ').map((part) => part[0] || '').join('').slice(0, 2).toUpperCase();
}

function showAuthFeedback(message, type = 'error') {
  const feedback = $('#auth-feedback');
  if (!feedback) return;
  feedback.textContent = message;
  feedback.className = `auth-feedback ${type}`;
}

function clearAuthFeedback() {
  const feedback = $('#auth-feedback');
  if (!feedback) return;
  feedback.textContent = '';
  feedback.className = 'auth-feedback hidden';
}

function clearAuthForms() {
  ['#login-email', '#login-password', '#register-name', '#register-email', '#register-password', '#register-confirm-password'].forEach((selector) => {
    const input = $(selector);
    if (input) input.value = '';
  });
}

function setAuthMode(mode) {
  state.authMode = mode;
  clearAuthFeedback();
  renderAuth();
}

function completeLogin(account) {
  state.authenticated = true;
  state.currentUser = account;
  saveSession();
  clearAuthFeedback();
  setScreen('home');
}

function login() {
  const emailInput = $('#login-email');
  const passInput = $('#login-password');
  if (!emailInput || !passInput) return;
  const email = emailInput.value.trim().toLowerCase();
  const password = passInput.value;
  if (!email || !password) {
    showAuthFeedback('Fill in the email and password.');
    return;
  }
  const account = getAccounts().find((item) => item.email.toLowerCase() === email && item.password === password);
  if (!account) {
    showAuthFeedback('Account not found. You can create a new account in the registration tab.');
    return;
  }
  completeLogin(account);
}

function register() {
  const name = $('#register-name')?.value.trim();
  const email = $('#register-email')?.value.trim().toLowerCase();
  const password = $('#register-password')?.value;
  const confirmPassword = $('#register-confirm-password')?.value;
  const accounts = getAccounts();
  if (!name || !email || !password || !confirmPassword) {
    showAuthFeedback('Fill in all fields to create the account.');
    return;
  }
  if (!email.includes('@')) {
    showAuthFeedback('Enter a valid email.');
    return;
  }
  if (password.length < 6) {
    showAuthFeedback('The password must be at least 6 characters long.');
    return;
  }
  if (password !== confirmPassword) {
    showAuthFeedback('Passwords do not match.');
    return;
  }
  if (accounts.some((account) => account.email.toLowerCase() === email)) {
    showAuthFeedback('An account with this email already exists.');
    return;
  }
  const newAccount = { name, email, password };
  accounts.push(newAccount);
  saveAccounts(accounts);
  if($('#login-email')) $('#login-email').value = email;
  if($('#login-password')) $('#login-password').value = password;
  setAuthMode('login');
  showAuthFeedback('Account created successfully. Now just sign in.', 'success');
}

function logout() {
  state.authenticated = false;
  state.currentUser = null;
  state.authMode = 'login';
  saveSession();
  clearAuthForms();
  setScreen('auth');
}

function setScreen(screen) {
  const targetScreen = state.authenticated || screen === 'auth' ? screen : 'auth';
  state.screen = targetScreen;
  $$('.screen').forEach((el) => el.classList.remove('active'));
  const nextScreen = $(`#screen-${targetScreen}`);
  if (nextScreen) nextScreen.classList.add('active');
  $$('.nav-btn').forEach((btn) => {
    btn.classList.toggle('active', btn.dataset.nav === targetScreen);
  });
  render();
}

function openModal(id) {
  const modal = $(`#${id}`);
  if (modal) modal.classList.add('active');
}

function closeModal(id) {
  const modal = $(`#${id}`);
  if (modal) modal.classList.remove('active');
}

function renderAuth() {
  $$('[data-auth-mode]').forEach((btn) => {
    btn.classList.toggle('active', btn.dataset.authMode === state.authMode);
  });
  if($('#auth-login-form')) $('#auth-login-form').classList.toggle('hidden', state.authMode !== 'login');
  if($('#auth-register-form')) $('#auth-register-form').classList.toggle('hidden', state.authMode !== 'register');
}

function renderHome() {
  const currentUserName = state.currentUser?.name || 'User';
  const homeGreeting = $('#screen-home .brand h1');
  if (homeGreeting) homeGreeting.textContent = `Hello, ${currentUserName} 👋`;

  const myTrips = state.trips.filter(trip => trip.members.includes(currentUserName) || trip.members.includes('You'));

  const allPending = myTrips.flatMap((trip) =>
    trip.pendingActions
      .filter(action => !action.targetUsers || action.targetUsers.includes(currentUserName))
      .map((action) => ({ ...action, tripId: trip.id, tripName: trip.name }))
  );

  const countLabel = $('#pending-count-label');
  if(countLabel) countLabel.textContent = `${allPending.length} item${allPending.length === 1 ? '' : 's'}`;
  
  const tripCountLabel = $('#trip-count-label');
  if(tripCountLabel) tripCountLabel.textContent = `${myTrips.length} trip${myTrips.length === 1 ? '' : 's'}`;

  const pendingContainer = $('#pending-actions-list');
  if (pendingContainer) {
    if (!allPending.length) {
      pendingContainer.innerHTML = '<div class="empty-state">No pending actions right now :)</div>';
    } else {
      pendingContainer.innerHTML = allPending.map((action) => `
        <div class="action-card">
          <strong>${action.tripName}</strong>
          <div class="muted">${action.description}</div>
          <div class="spacer-8"></div>
          <button class="small-link" onclick="openTripFromAction(${action.tripId})">${action.cta}</button>
        </div>
      `).join('');
    }
  }
  
  const homeTripList = $('#home-trip-list');
  if(homeTripList) homeTripList.innerHTML = myTrips.map(renderTripCard).join('');
  checkAndShowInvitePopup();
}

function renderTripCard(trip) {
  const memberCount = (trip.acceptedMembers || []).length;
  const badgeText = trip.status === 'closed' ? 'Closed' : trip.votesCompleted < trip.votesTotal ? 'Planning' : 'Ongoing';
  const badgeClass = trip.status === 'closed' ? 'closed' : trip.votesCompleted < trip.votesTotal ? 'planning' : 'progress';
  const statusLine = trip.status === 'closed' ? 'Accounts settled' : trip.missingItem ? `Missing ${trip.missingItem}` : 'Trip ready';

  return `
    <div class="trip-card" onclick="openTrip(${trip.id})">
      <div class="trip-card-content">
        <div class="trip-info-side">
          <h4 class="trip-title">
            ${trip.name} <span class="status-arrow">→</span> <span class="status-text ${badgeClass}">${badgeText}</span>
          </h4>
          <div class="trip-meta">${trip.start} to ${trip.end} • ${memberCount} members</div>
          <div class="muted" style="margin-top: 8px; font-size: 0.85rem;">${statusLine}</div>
        </div>

        <div class="trip-actions-side">
          <button class="delete-btn-elegant" onclick="deleteTrip(event, ${trip.id})">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg>
          </button>
          <span class="votes-badge">${trip.votesCompleted}/${trip.votesTotal} votes</span>
        </div>
      </div>
    </div>
  `;
}


function showVotingDoneToast(message) {
  const existing = document.getElementById('voting-done-toast');
  if (existing) existing.remove();

  const activeScreen = document.querySelector('.screen.active') || document.body;
  const notifBox = document.createElement('div');
  notifBox.id = 'voting-done-toast';
  
  notifBox.style.cssText = `
    position: absolute;
    top: 80px;
    left: 50%;
    transform: translateX(-50%);
    width: 90%;
    box-sizing: border-box;
    background-color: #0284c7; 
    color: white;
    padding: 14px 16px;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    font-family: inherit;
    font-size: 13px;
    z-index: 9999;
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 10px;
    transition: opacity 0.3s ease;
  `;

  if (window.getComputedStyle(activeScreen).position === 'static') {
    activeScreen.style.position = 'relative';
  }

  const textSpan = document.createElement('span');
  textSpan.innerText = message;
  textSpan.style.flex = "1";

  const timerSpan = document.createElement('span');
  timerSpan.style.cssText = `
    background: rgba(255,255,255,0.2);
    padding: 4px 8px;
    border-radius: 6px;
    font-weight: bold;
    white-space: nowrap;
    font-variant-numeric: tabular-nums;
  `;

  notifBox.appendChild(textSpan);
  notifBox.appendChild(timerSpan);
  activeScreen.appendChild(notifBox);

  let timeLeft = 10;
  timerSpan.innerText = `${timeLeft}s`;

  const interval = setInterval(() => {
    timeLeft--;
    if (timeLeft > 0) {
      timerSpan.innerText = `${timeLeft}s`;
    } else {
      clearInterval(interval);
      notifBox.style.opacity = '0';
      setTimeout(() => notifBox.remove(), 300);
    }
  }, 1000);
}


function checkAndShowVotingDoneNotification() {
  const trip = getCurrentTrip();
  if (!trip || !trip.votesConfirmed) return;
  const currentUserName = state.currentUser?.name;
  if (!currentUserName) return;

  if (trip.votedMembers && !trip.votedMembers.includes(currentUserName)) {
    return;
  }

  const finishedTrip = state.trips.find(trip => {
    const isMember = trip.acceptedMembers?.includes(currentUserName);
    
    const isVotingPhaseOver = trip.votesConfirmed === true;

    if (!trip.notifiedVotingDone) trip.notifiedVotingDone = [];
    const alreadyNotified = trip.notifiedVotingDone.includes(currentUserName);

    return isMember && isVotingPhaseOver && !alreadyNotified;
  });

  if (!finishedTrip) return;

  finishedTrip.notifiedVotingDone.push(currentUserName);
  saveTripsToStorage();

  showVotingDoneToast("Vote phase is done!! Go to itenerary and vote to stay or leave");
}

function checkAndShowToastEvents() {
  const currentUserName = state.currentUser?.name;
  if (!currentUserName) return;

  let needsSave = false;

  state.trips.forEach(trip => {
    if (!trip.toastEvents) return;

    trip.toastEvents.forEach(event => {
      if (event.targetUsers.includes(currentUserName) && !event.notifiedUsers.includes(currentUserName)) {

        const isErrorToast = event.type === 'MEMBER_LEFT' || event.type === 'INVITE_DECLINED';
        showActionToast(event.message, isErrorToast);

        event.notifiedUsers.push(currentUserName);
        needsSave = true;
      }
    });
  });

  if (needsSave) {
    saveTripsToStorage();
  }
}

function renderTrips() {
  const currentUserName = state.currentUser?.name || 'User';
  const myTrips = state.trips.filter(trip => trip.members.includes(currentUserName) || trip.members.includes('You'));

  const isClosed = state.tripsFilter === 'closed';
  const filtered = myTrips.filter((trip) => isClosed ? trip.status === 'closed' : trip.status !== 'closed');
  
  const tripsList = $('#trips-list');
  if(tripsList) {
    tripsList.innerHTML = filtered.length ? filtered.map(renderTripCard).join('') : '<div class="empty-state">No trips in this section yet.</div>';
  }
  $$('[data-trip-filter]').forEach((btn) => {
    btn.classList.toggle('active', btn.dataset.tripFilter === state.tripsFilter);
  });
}

function renderTripDetail() {
  const trip = getCurrentTrip();
  if (!trip) return;
  const currentUserName = state.currentUser?.name || 'You';

  if (!trip.acceptedMembers) trip.acceptedMembers = [...trip.members];
  const hasAccepted = trip.acceptedMembers.includes(currentUserName);

  const topbarBrand = $('#screen-trip-detail .brand.centered-brand');
  const finishBtn = $('#trip-finish-btn');
  const tabRow = $('#screen-trip-detail .tab-row');

  if (!hasAccepted) {
    if (topbarBrand) topbarBrand.style.visibility = 'hidden'; 
    if (finishBtn) finishBtn.style.display = 'none'; 
    if (tabRow) tabRow.style.display = 'none'; 
  } else {
    if (topbarBrand) topbarBrand.style.visibility = 'visible';
    if (finishBtn) finishBtn.style.display = 'block';
    if (tabRow) tabRow.style.display = 'flex';
  }

  if($('#trip-detail-title')) $('#trip-detail-title').textContent = trip.name;
  if($('#trip-detail-subtitle')) $('#trip-detail-subtitle').textContent = `${trip.start} to ${trip.end} • ${(trip.acceptedMembers || []).length} members`;
  if($('#vote-progress-label')) $('#vote-progress-label').textContent = `${trip.votesCompleted}/${trip.votesTotal} completed`;

  const votesTab = $('#trip-tab-votes');
  if (votesTab) {
    if (!hasAccepted) {
      votesTab.innerHTML = `
        <div class="panel" style="text-align: center; padding: 40px 20px; background: white; border: 1px solid var(--border); border-radius: 20px; margin-top: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.04);">
          <div style="font-size: 3.5rem; margin-bottom: 16px;">💌</div>
          <h3 style="margin-bottom: 8px; color: #1e293b;">${trip.creator} invited you to ${trip.name}</h3>
          <p class="muted" style="margin-bottom: 24px;">Accept the invitation to join the group, view the itinerary, and vote for the destination.</p>
          <div class="btn-row">
            <button class="danger-btn" style="background: #fff1f1; color: var(--danger);" onclick="respondToInvite(false)">Decline</button>
            <button class="primary-btn" onclick="respondToInvite(true)">Accept & Join</button>
          </div>
        </div>
      `;
    } 
    else if (trip.votesCompleted >= trip.votesTotal || trip.votesConfirmed) {
      votesTab.innerHTML = `
        <div class="section-title">
          <h3>Winning decisions</h3>
          <span>${trip.votesCompleted}/${trip.votesTotal} completed</span>
        </div>
        <div class="panel">
          <h4>Winning destination</h4>
          <div><strong>📍 ${trip.voteResults.destination}</strong></div>
        </div>
        <div class="panel">
          <h4>Winning accommodation</h4>
          <div><strong>🏠 ${trip.voteResults.accommodation}</strong></div>
        </div>
        <div class="panel">
          <h4>Approved activities</h4>
          <div id="approved-activities">
            ${trip.approvedActivities.length 
              ? trip.approvedActivities.map(a => `<div class="timeline-item">${a.name} <span class="muted">(€${a.price})</span></div>`).join('') 
              : '<div class="empty-state">No approved activities yet.</div>'}
          </div>
        </div>
        <div class="spacer-8"></div>
        <button class="secondary-btn" id="export-pdf-btn">Export PDF</button>
      `;
    } else {
      const hasVoted = trip.votedMembers && trip.votedMembers.includes(currentUserName);
      votesTab.innerHTML = `
        <div class="section-title">
          <h3>Voting in progress</h3>
          <span>${trip.votesCompleted}/${trip.votesTotal} completed</span>
        </div>
        <div class="empty-state" style="padding: 40px 20px; background: white; border-radius: 20px; border: 1px solid #e2e8f0; margin-bottom: 20px;">
          <div style="font-size: 3rem; margin-bottom: 16px;">🗳️</div>
          <strong style="font-size: 1.1rem; color: #1e293b;">Awaiting group decisions</strong>
          <div class="spacer-8"></div>
          <p class="muted">The final destination and activities will be revealed here once all members finish voting.</p>
        </div>
        ${!hasVoted ? `<button class="primary-btn" onclick="setScreen('suggestions')">Vote now</button>` : ''}
      `;
    }
  }

  const itineraryDay = trip.itinerary[0] || { day: 1, items: ['Trip created successfully'] };
  if($('#itinerary-day-label')) $('#itinerary-day-label').textContent = `Day ${itineraryDay.day}`;
  
  let confirmPresenceHtml = '';
  if (trip.votesConfirmed && trip.members.includes(currentUserName) && (!trip.participationConfirmed || !trip.participationConfirmed.includes(currentUserName))) {
      confirmPresenceHtml = `
        <div class="panel" style="border: 2px solid var(--primary); background: #f4f7ff; margin-bottom: 16px;">
          <div style="display:flex; align-items:center; gap: 8px; margin-bottom: 8px;">
             <span style="font-size: 1.5rem;">🎒</span>
             <h4 style="margin:0;">Are you going to ${trip.voteResults.destination}?</h4>
          </div>
          <p class="muted" style="margin-top:0;">The group has decided! Confirm if you are still joining the trip.</p>
          <div class="spacer-8"></div>
          <div class="btn-row">
            <button class="danger-btn" style="background: white; border: 1px solid var(--danger); color: var(--danger);" onclick="confirmParticipation(false)">No, I'm out</button>
            <button class="primary-btn" onclick="confirmParticipation(true)">Yes, I'm in!</button>
          </div>
        </div>
      `;
  }

  if($('#itinerary-current')) {
    $('#itinerary-current').innerHTML = confirmPresenceHtml + itineraryDay.items.map((item) => `<div class="timeline-item">${item}</div>`).join('');
  }

  const myExpenses = trip.expenses.filter(expense => {
    if (!expense.participantNames) return true;
    
    const payerName = expense.paidBy || ''; 
    const isPayer = payerName.trim().toLowerCase() === currentUserName.trim().toLowerCase();
    
    const isParticipant = expense.participantNames.map(n => (n || '').trim().toLowerCase()).includes(currentUserName.trim().toLowerCase());
    
    return isPayer || isParticipant; 
  });

  const totalSpent = myExpenses.reduce((sum, expense) => sum + Number(expense.amount || 0), 0);
  const pendingExpenses = myExpenses.filter((expense) => expense.pending);
  
  if($('#group-total-spent')) $('#group-total-spent').textContent = formatCurrency(totalSpent);
  if($('#pending-balance-count')) $('#pending-balance-count').textContent = `${pendingExpenses.length} pending`;

  if($('#member-list-inline')) {
    const membrosVisiveis = trip.acceptedMembers || [];
    $('#member-list-inline').innerHTML = membrosVisiveis.map((member) => {
      const isMe = (member || '').trim().toLowerCase() === currentUserName.trim().toLowerCase();
      
      return `
        <div class="member-row">
          <div class="member-info">
            <div class="avatar">${initials(member)}</div>
            <div>
              <strong>${member}</strong>
              <div class="muted">${member === trip.creator ? 'Trip Organizer' : 'Trip Member'}</div>
            </div>
          </div>
          ${isMe 
            ? `<button class="small-link" style="color: var(--danger);" onclick="removeMember('${member.replace(/'/g, "\\'")}')">Leave trip</button>` 
            : ''}
        </div>
      `;
    }).join('');
  }

  const expList = $('#expense-list');
  if (expList) {
    expList.innerHTML = myExpenses.length ? myExpenses.map((expense) => `
      <div class="expense-item ${expense.pending ? 'pending' : 'settled'}">
        <div class="row-between">
          <div>
            <strong>${expense.title}</strong>
            <div class="expense-note">Split between ${expense.participants} people (${expense.paidBy} paid)</div>
          </div>
          <div class="expense-amount">€${Number(expense.amount).toFixed(2)}</div>
        </div>
        <div class="spacer-8"></div>
        <div class="muted">Each person owes <strong>€${Number(expense.owedPerPerson).toFixed(2)}</strong></div>
        <div class="spacer-8"></div>
        ${expense.pending
          ? `<button class="secondary-btn" onclick="markExpensePaid(${trip.id}, ${expense.id})">Mark as settled ✅</button>`
          : '<div class="muted">Settled ✅</div>'
        }
      </div>
    `).join('') : '<div class="empty-state">No expenses yet.</div>';
  }

  $$('[data-detail-tab]').forEach((btn) => {
    btn.classList.toggle('active', btn.dataset.detailTab === state.detailTab);
    if (btn.dataset.detailTab === 'votes') btn.style.display = trip.votesConfirmed ? 'none' : 'block'; 
  });

  if($('#trip-tab-votes')) $('#trip-tab-votes').classList.toggle('hidden', state.detailTab !== 'votes');
  if($('#trip-tab-itinerary')) $('#trip-tab-itinerary').classList.toggle('hidden', state.detailTab !== 'itinerary');
  if($('#trip-tab-members')) $('#trip-tab-members').classList.toggle('hidden', state.detailTab !== 'members');
  if($('#trip-tab-expenses')) $('#trip-tab-expenses').classList.toggle('hidden', state.detailTab !== 'expenses');
}

function markExpensePaid(tripId, expenseId) {
  const trip = state.trips.find((item) => item.id === tripId);
  const expense = trip?.expenses.find((item) => item.id === expenseId);
  if (expense) { 
    expense.pending = false; 
  }
  
  if (trip && trip.pendingActions) {
    trip.pendingActions = trip.pendingActions.filter(action => !(action.type === 'expense' && action.expenseId === expenseId));
  }

  saveTripsToStorage();
  render();
}

function formatInvitedNames(names) {
  if (!names || names.length === 0) return "";
  if (names.length === 1) return names[0];
  if (names.length === 2) return `${names[0]} e ${names[1]}`;

  const namesCopy = [...names];
  const last = namesCopy.pop();
  return `${namesCopy.join(', ')} e ${last}`;
}

function showTripNotification(invitedUsers) {
  const existing = document.getElementById('trip-notification');
  if (existing) existing.remove();

  let message = "Trip created successfully!";
  if (invitedUsers && invitedUsers.length > 0) {
    const namesText = formatInvitedNames(invitedUsers);
    message = `Trip created and invites sent to ${namesText} successfully!`;
  }

  const activeScreen = document.querySelector('.screen.active') || document.body;

  const notifBox = document.createElement('div');
  notifBox.id = 'trip-notification';

  notifBox.style.cssText = `
    position: absolute;
    top: 80px; /* Alinhado no topo, logo abaixo do cabeçalho da app */
    left: 50%;
    transform: translateX(-50%);
    width: 90%;
    box-sizing: border-box;
    background-color: #2e7d32;
    color: white;
    padding: 14px 16px;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    font-family: inherit;
    font-size: 13px;
    z-index: 9999;
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 10px;
    transition: opacity 0.3s ease;
  `;

  if (window.getComputedStyle(activeScreen).position === 'static') {
    activeScreen.style.position = 'relative';
  }

  const textSpan = document.createElement('span');
  textSpan.innerText = message;
  textSpan.style.flex = "1";

  const timerSpan = document.createElement('span');
  timerSpan.style.cssText = `
    background: rgba(255,255,255,0.2);
    padding: 4px 8px;
    border-radius: 6px;
    font-weight: bold;
    white-space: nowrap;
    font-variant-numeric: tabular-nums;
  `;

  notifBox.appendChild(textSpan);
  notifBox.appendChild(timerSpan);
  
  activeScreen.appendChild(notifBox);

  let timeLeft = 10;
  timerSpan.innerText = `${timeLeft}s`;

  const interval = setInterval(() => {
    timeLeft--;
    if (timeLeft > 0) {
      timerSpan.innerText = `${timeLeft}s`;
    } else {
      clearInterval(interval);
      notifBox.style.opacity = '0';
      setTimeout(() => notifBox.remove(), 300);
    }
  }, 1000);
}

function respondToInvite(accepted) {
  const trip = getCurrentTrip();
  if (!trip) return;
  const currentUserName = state.currentUser?.name || 'You';

  if (accepted) {
    if (!trip.acceptedMembers) trip.acceptedMembers = [];
    if (!trip.acceptedMembers.includes(currentUserName)) {
      trip.acceptedMembers.push(currentUserName);
    }

    const inviteAction = trip.pendingActions.find(a => a.type === 'invite');
    if (inviteAction && inviteAction.targetUsers) {
      inviteAction.targetUsers = inviteAction.targetUsers.filter(u => u !== currentUserName);
    }

    if (trip.votesConfirmed) {
        if (!trip.participationConfirmed) trip.participationConfirmed = [];
        if (!trip.participationConfirmed.includes(currentUserName)) {
            trip.participationConfirmed.push(currentUserName);
        }
    } else {
        let voteAction = trip.pendingActions.find(a => a.type === 'vote' || a.title === 'Voting in progress');
        if (voteAction) {
          if (!voteAction.targetUsers) voteAction.targetUsers = [];
          if (!voteAction.targetUsers.includes(currentUserName)) {
              voteAction.targetUsers.push(currentUserName);
          }
        }
    }

    if (!trip.toastEvents) trip.toastEvents = [];
    trip.toastEvents.push({
      id: Date.now(),
      type: 'INVITE_ACCEPTED',
      message: `${currentUserName} joined the trip "${trip.name}".`,
      targetUsers: trip.members.filter(m => m !== currentUserName), 
      notifiedUsers: []
    });

    saveTripsToStorage();
    render();
  } else {
    if (confirm('Are you sure you want to decline this invitation?')) {
      trip.members = trip.members.filter(m => m !== currentUserName);
      
      const inviteAction = trip.pendingActions.find(a => a.type === 'invite');
      if (inviteAction && inviteAction.targetUsers) {
        inviteAction.targetUsers = inviteAction.targetUsers.filter(u => u !== currentUserName);
      }
      
      if (!trip.toastEvents) trip.toastEvents = [];
      trip.toastEvents.push({
        id: Date.now(),
        type: 'INVITE_DECLINED',
        message: `${currentUserName} declined the invite to "${trip.name}".`,
        targetUsers: [...trip.members],
        notifiedUsers: []
      });

      saveTripsToStorage();
      setScreen('home');
      render();
    }
  }
}


function showActionToast(message, isError = false) {
  const existing = document.getElementById('action-toast');
  if (existing) existing.remove();

  const activeScreen = document.querySelector('.screen.active') || document.body;
  const notifBox = document.createElement('div');
  notifBox.id = 'action-toast';
  
  const bgColor = isError ? '#d32f2f' : '#2e7d32';

  notifBox.style.cssText = `
    position: absolute;
    top: 80px;
    left: 50%;
    transform: translateX(-50%);
    width: 90%;
    box-sizing: border-box;
    background-color: ${bgColor};
    color: white;
    padding: 14px 16px;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    font-family: inherit;
    font-size: 13px;
    z-index: 9999;
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 10px;
    transition: opacity 0.3s ease;
  `;

  if (window.getComputedStyle(activeScreen).position === 'static') {
    activeScreen.style.position = 'relative';
  }

  const textSpan = document.createElement('span');
  textSpan.innerText = message;
  textSpan.style.flex = "1";

  const timerSpan = document.createElement('span');
  timerSpan.style.cssText = `
    background: rgba(255,255,255,0.2);
    padding: 4px 8px;
    border-radius: 6px;
    font-weight: bold;
    white-space: nowrap;
    font-variant-numeric: tabular-nums;
  `;

  notifBox.appendChild(textSpan);
  notifBox.appendChild(timerSpan);
  activeScreen.appendChild(notifBox);

  let timeLeft = 10;
  timerSpan.innerText = `${timeLeft}s`;

  const interval = setInterval(() => {
    timeLeft--;
    if (timeLeft > 0) {
      timerSpan.innerText = `${timeLeft}s`;
    } else {
      clearInterval(interval);
      notifBox.style.opacity = '0';
      setTimeout(() => notifBox.remove(), 300);
    }
  }, 1000);
}


function checkAndShowInvitePopup() {
  const currentUserName = state.currentUser?.name || 'User';
  
  const pendingTrip = state.trips.find(trip =>
    trip.pendingActions?.some(a => a.type === 'invite' && a.targetUsers?.includes(currentUserName))
  );

  if (!pendingTrip) return;

  if (document.getElementById('invite-popup-overlay')) return;

  const overlay = document.createElement('div');
  overlay.id = 'invite-popup-overlay';
  overlay.style.cssText = `
    position: fixed; top: 0; left: 0; width: 100%; height: 100%;
    background: rgba(0,0,0,0.5); z-index: 10000;
    display: flex; align-items: center; justify-content: center;
  `;

  const popup = document.createElement('div');
  popup.style.cssText = `
    background: white; width: 85%; max-width: 350px;
    border-radius: 16px; padding: 24px; box-shadow: 0 10px 25px rgba(0,0,0,0.2);
    text-align: center; position: relative; overflow: hidden;
  `;

  function renderMainView() {
    popup.innerHTML = `
      <div style="font-size: 3rem; margin-bottom: 12px;">💌</div>
      <h3 style="margin: 0 0 12px; color: #1e293b; font-size: 1.2rem;">
        You have been invited to join the trip ${pendingTrip.name}
      </h3>
      <div style="display: flex; flex-direction: column; gap: 10px;">
        <button class="primary-btn" id="btn-aceitar" style="width: 100%;">Accept</button>
        <button class="danger-btn" id="btn-recusar" style="width: 100%; background: #fff1f1; color: var(--danger);">Decline</button>
        <button class="secondary-btn" id="btn-detalhes" style="width: 100%; background: #f1f5f9; color: #475569; border: none;">View trip details</button>
      </div>
    `;

    popup.querySelector('#btn-aceitar').onclick = () => processInvite(true);
    popup.querySelector('#btn-recusar').onclick = () => processInvite(false);
    popup.querySelector('#btn-detalhes').onclick = renderDetailsView;
  }

  function renderDetailsView() {
    popup.innerHTML = `
      <h3 style="margin: 0 0 16px; color: #1e293b; font-size: 1.2rem;">Trip Details</h3>
      <div style="text-align: left; background: #f8fafc; padding: 16px; border-radius: 12px; margin-bottom: 20px; font-size: 0.95rem; color: #334155;">
        <p style="margin: 0 0 8px;"><strong>Destination:</strong> ${pendingTrip.destination || 'To be decided'}</p>
        <p style="margin: 0 0 8px;"><strong>Creator:</strong> ${pendingTrip.creator}</p>
        <p style="margin: 0 0 8px;"><strong>Dates:</strong> ${pendingTrip.start} to ${pendingTrip.end}</p>
        <p style="margin: 0;"><strong>Average Budget:</strong> €${pendingTrip.budget || 0}</p>
      </div>
      <button class="secondary-btn" id="btn-voltar" style="width: 100%;">⬅ Back</button>
    `;
    popup.querySelector('#btn-voltar').onclick = renderMainView;
  }

  function processInvite(accepted) {
    const trip = state.trips.find(t => t.id === pendingTrip.id);
    if (!trip) return;

    if (accepted) {
      if (!trip.acceptedMembers) trip.acceptedMembers = [];
      if (!trip.acceptedMembers.includes(currentUserName)) {
        trip.acceptedMembers.push(currentUserName);
      }

      const inviteAction = trip.pendingActions.find(a => a.type === 'invite');
      if (inviteAction && inviteAction.targetUsers) {
          inviteAction.targetUsers = inviteAction.targetUsers.filter(u => u !== currentUserName);
      }

      if (trip.votesConfirmed) {
          if (!trip.participationConfirmed) trip.participationConfirmed = [];
          if (!trip.participationConfirmed.includes(currentUserName)) {
              trip.participationConfirmed.push(currentUserName);
          }
      } else {
          let voteAction = trip.pendingActions.find(a => a.type === 'vote' || a.title === 'Voting in progress');
          if (voteAction && !voteAction.targetUsers.includes(currentUserName)) {
            voteAction.targetUsers.push(currentUserName);
          }
      }

      if (!trip.toastEvents) trip.toastEvents = [];
      trip.toastEvents.push({
        id: Date.now(),
        type: 'INVITE_ACCEPTED',
        message: `${currentUserName} joined the trip "${trip.name}".`,
        targetUsers: trip.members.filter(m => m !== currentUserName),
        notifiedUsers: []
      });

    } else {
      trip.members = trip.members.filter(m => m !== currentUserName);
      trip.votesTotal = Math.max(1, (trip.votesTotal || 1) - 1);
      const inviteAction = trip.pendingActions.find(a => a.type === 'invite');
      if (inviteAction) inviteAction.targetUsers = inviteAction.targetUsers.filter(u => u !== currentUserName);

      if (!trip.toastEvents) trip.toastEvents = [];
      trip.toastEvents.push({
        id: Date.now(),
        type: 'INVITE_DECLINED',
        message: `${currentUserName} declined the invite to "${trip.name}".`,
        targetUsers: [...trip.members],
        notifiedUsers: []
      });
    }

    saveTripsToStorage();
    
    const overlay = document.getElementById('invite-popup-overlay');
    if (overlay) overlay.remove();
    
    render(); 
    
    if (accepted) {
      setScreen('trip-detail');
      showActionToast(`Welcome to ${trip.name}`);
    } else {
      showActionToast(`Invite declined`, true);
    }
  }

  renderMainView();
  overlay.appendChild(popup);
  document.body.appendChild(overlay);
}

function confirmVotes(confirmed) {
  const trip = getCurrentTrip();
  if (!trip) return;
  const currentUserName = state.currentUser?.name || 'You';

  if (confirmed) {
    if (!trip.votedMembers) trip.votedMembers = [trip.creator || 'You'];
    
    if (!trip.votedMembers.includes(currentUserName)) {
      trip.votedMembers.push(currentUserName);
    }
    trip.votesCompleted = trip.votedMembers.length;
    
    const liked = trip.likedSuggestions || [];
    liked.forEach(city => {
      const sug = trip.suggestions.find(s => s.city === city);
      if (sug) sug.votes = (sug.votes || 0) + 1;
    });

    if (trip.votesCompleted >= trip.votesTotal) {
      const winner = trip.suggestions.reduce((max, obj) => (obj.votes > max.votes) ? obj : max, trip.suggestions[0]);
      
      trip.voteResults.destination = winner.city;
      trip.votesConfirmed = true;
      trip.voteResults.accommodation = 'Airbnb em De Pijp (Quarto para 4)';
      trip.approvedActivities = [
        { name: '🚤 Passeio de Barco pelos Canais', price: 15 },
        { name: '🚲 Aluguer de Bicicleta (Dia todo)', price: 12 },
        { name: '🎨 Museu Van Gogh', price: 22 }
      ];
      trip.itinerary = [{
        day: 1,
        nowNextTitle: 'Votes confirmed!',
        items: [`✅ Destination: ${winner.city}`]
      }];
      
      trip.participationConfirmed = []; 
      trip.pendingActions = [{
        title: 'Confirm presence',
        description: `Destination decided: ${winner.city}. Are you still going?`,
        cta: 'Confirm now',
        targetUsers: [...trip.members]
      }];
      trip.missingItem = 'Confirmations';
      
    } else {
      const action = trip.pendingActions.find(a => a.type === 'vote' || a.title === 'Voting in progress');
      if (action && action.targetUsers) {
        action.targetUsers = action.targetUsers.filter(u => u !== currentUserName);
      }
      trip.currentSuggestionIndex = 0;
      trip.likedSuggestions = [];
    }

    saveTripsToStorage();
    render();
    setScreen('trip-detail');
  } else {
    resetVotes();
  }
}

function renderSuggestions() {
  const trip = getCurrentTrip();
  if (!trip) return;

  if($('#suggestions-title')) $('#suggestions-title').textContent = `${trip.name} suggestions`;

  if (trip.currentSuggestionIndex === undefined) trip.currentSuggestionIndex = 0;
  if (!trip.likedSuggestions) trip.likedSuggestions = [];

  const index = trip.currentSuggestionIndex;
  const container = $('#suggestion-deck');
  if (!container) return;

  if (trip.votesConfirmed) {
    container.innerHTML = `
      <div class="empty-state" style="padding: 28px 18px; text-align: center;">
        <div style="font-size: 2.5rem; margin-bottom: 14px;">📍</div>
        <strong style="font-size: 1.1rem;">All suggestions reviewed</strong>
        <div class="spacer-8"></div>
        <button class="primary-btn" onclick="setScreen('trip-detail')">Back to Trip</button>
      </div>`;
    return;
  }

  if (index >= trip.suggestions.length) {
    renderVoteConfirmation(trip, container);
    return;
  }

  const suggestion = trip.suggestions[index];

  container.innerHTML = `
  <div class="suggestion-card" id="active-suggestion-card" style="position: relative;">
    <div class="swipe-stamp stamp-like" id="stamp-like">LIKE</div>
    <div class="swipe-stamp stamp-skip" id="stamp-skip">SKIP</div>
    
    <div class="suggestion-image">
      <img src="${suggestion.image}" alt="${suggestion.city}">
      <div class="rating-badge">⭐ ${suggestion.rating || '—'}</div>
    </div>

    <div class="suggestion-body">
      <div class="tag-row">
        ${(suggestion.tags || []).map(tag => `<span class="tag-chip">${tag}</span>`).join('')}
        ${suggestion.votes > 0 ? `<span class="tag-chip" style="background: #eef2ff; color: #4f46e5; font-weight: 800;">👍 ${suggestion.votes} vote</span>` : ''}
      </div>
      <h3>${suggestion.city}</h3>
      <p class="muted">${suggestion.subtitle}</p>

      <div class="spacer-16"></div>
      <div class="price-row">
        <span class="muted">Average cost</span>
        <div class="big-number">${formatCurrency(suggestion.avgCost)}</div>
      </div>
      
      <div class="swipe-hint">
        &larr; Swipe Left to Skip | Swipe Right to Like &rarr;
      </div>
    </div>
  </div>
`;

  attachSwipeListeners();
}

function renderVoteConfirmation(trip, container) {
  const liked = trip.likedSuggestions || [];

  let bodyHtml;
  if (liked.length === 0) {
    bodyHtml = `
      <div class="vote-confirm-none">
        <div style="font-size: 2.5rem; margin-bottom: 12px;">🤔</div>
        <p style="font-weight: 700; font-size: 1.05rem; margin: 0 0 6px;">No destinations selected.</p>
      </div>
    `;
  } else {
    const names = liked.map(c => `<strong>${c}</strong>`).join(', ');
    bodyHtml = `
      <div class="vote-confirm-header">
        <div style="font-size: 2.5rem; margin-bottom: 12px;">🗳️</div>
        <p class="vote-confirm-title">
          The destinations you selected:<br>${names}
        </p>
      </div>
      <div class="vote-confirm-actions">
        <button class="primary-btn" onclick="confirmVotes(true)" style="margin-bottom: 10px;">✅ Confirm</button>
      </div>
    `;
  }

  container.innerHTML = `
    <div class="vote-confirm-card">
      ${bodyHtml}
      <div class="spacer-16"></div>
      <button class="secondary-btn" onclick="resetVotes()">🔄 Vote again</button>
    </div>
  `;
}

function attachSwipeListeners() {
  const card = $('#active-suggestion-card');
  if (!card) return;

  let startX = 0;
  let currentX = 0;
  let isDragging = false;
  const stampLike = $('#stamp-like');
  const stampSkip = $('#stamp-skip');

  const onStart = (e) => {
    isDragging = true;
    startX = e.pageX || e.touches[0].pageX;
    card.style.transition = 'none';
  };

  const onMove = (e) => {
    if (!isDragging) return;
    currentX = e.pageX || (e.touches ? e.touches[0].pageX : 0);
    const diff = currentX - startX;
    card.style.transform = `translateX(${diff}px) rotate(${diff / 15}deg)`;

    if (diff > 50) {
      card.style.background = '#ecfdf3';
      if (stampLike) stampLike.style.opacity = Math.min(1, (diff - 50) / 80);
      if (stampSkip) stampSkip.style.opacity = 0;
    } else if (diff < -50) {
      card.style.background = '#fff0f0';
      if (stampSkip) stampSkip.style.opacity = Math.min(1, (-diff - 50) / 80);
      if (stampLike) stampLike.style.opacity = 0;
    } else {
      card.style.background = 'white';
      if (stampLike) stampLike.style.opacity = 0;
      if (stampSkip) stampSkip.style.opacity = 0;
    }
  };

  const onEnd = () => {
    if (!isDragging) return;
    isDragging = false;
    const diff = currentX - startX;
    const threshold = 100;

    if (diff > threshold) {
      card.style.transition = 'transform 0.3s ease';
      card.style.transform = 'translateX(1000px) rotate(30deg)';
      setTimeout(() => swipeSuggestion('like'), 200);
    } else if (diff < -threshold) {
      card.style.transition = 'transform 0.3s ease';
      card.style.transform = 'translateX(-1000px) rotate(-30deg)';
      setTimeout(() => swipeSuggestion('skip'), 200);
    } else {
      card.style.transition = 'transform 0.3s ease, background 0.3s ease';
      card.style.transform = 'translateX(0) rotate(0)';
      card.style.background = 'white';
      if (stampLike) stampLike.style.opacity = 0;
      if (stampSkip) stampSkip.style.opacity = 0;
    }
  };

  card.addEventListener('mousedown', onStart);
  card.addEventListener('touchstart', onStart);
  window.addEventListener('mousemove', onMove);
  window.addEventListener('touchmove', onMove);
  window.addEventListener('mouseup', onEnd);
  window.addEventListener('touchend', onEnd);
}

function swipeSuggestion(type) {
  const trip = getCurrentTrip();
  if (!trip) return;

  if (trip.currentSuggestionIndex === undefined) trip.currentSuggestionIndex = 0;
  if (!trip.likedSuggestions) trip.likedSuggestions = [];

  const index = trip.currentSuggestionIndex;
  const suggestion = trip.suggestions[index];
  if (!suggestion) return;

  if (type === 'like') {
    if (!trip.likedSuggestions.includes(suggestion.city)) {
      trip.likedSuggestions.push(suggestion.city);
    }
  }

  trip.currentSuggestionIndex = index + 1;
  saveTripsToStorage();
  renderSuggestions();
}

function confirmVotes(confirmed) {
  const trip = getCurrentTrip();
  if (!trip) return;
  const currentUserName = state.currentUser?.name || 'You';

  if (confirmed) {
    if (!trip.votedMembers) trip.votedMembers = [trip.creator || 'You'];
    
    if (!trip.votedMembers.includes(currentUserName)) {
      trip.votedMembers.push(currentUserName);
    }
    trip.votesCompleted = trip.votedMembers.length;
    
    const liked = trip.likedSuggestions || [];
    liked.forEach(city => {
      const sug = trip.suggestions.find(s => s.city === city);
      if (sug) sug.votes = (sug.votes || 0) + 1;
    });

    if (trip.votesCompleted >= trip.votesTotal) {
      const winner = trip.suggestions.reduce((max, obj) => (obj.votes > max.votes) ? obj : max, trip.suggestions[0]);
      
      trip.voteResults.destination = winner.city;
      trip.votesConfirmed = true;
      trip.voteResults.accommodation = 'Airbnb em De Pijp (Quarto para 4)';
      trip.approvedActivities = [
        { name: '🚤 Passeio de Barco pelos Canais', price: 15 },
        { name: '🚲 Aluguer de Bicicleta (Dia todo)', price: 12 },
        { name: '🎨 Museu Van Gogh', price: 22 }
      ];
      trip.itinerary = [{
        day: 1,
        nowNextTitle: 'Votes confirmed!',
        items: [`✅ Destination: ${winner.city}`]
      }];

      trip.participationConfirmed = []; 
      trip.pendingActions = [{
        title: 'Confirm presence',
        description: `Destination decided: ${winner.city}. Are you still going?`,
        cta: 'Confirm now',
        targetUsers: [...trip.members]
      }];
      trip.missingItem = 'Confirmations';
      
    } else {
      const action = trip.pendingActions[0];
      if (action && action.targetUsers) {
        action.targetUsers = action.targetUsers.filter(u => u !== currentUserName);
      }
      trip.currentSuggestionIndex = 0;
      trip.likedSuggestions = [];
    }

    saveTripsToStorage();
    render();
    setScreen('trip-detail');
  } else {
    resetVotes();
  }
}

function confirmParticipation(isGoing) {
  const trip = getCurrentTrip();
  if (!trip) return;
  const currentUserName = state.currentUser?.name || 'You';

  if (isGoing) {
    if (!trip.participationConfirmed) trip.participationConfirmed = [];
    if (!trip.participationConfirmed.includes(currentUserName)) {
      trip.participationConfirmed.push(currentUserName);
    }
    
    const action = trip.pendingActions.find(a => a.title === 'Confirm presence');
    if (action && action.targetUsers) {
      action.targetUsers = action.targetUsers.filter(u => u !== currentUserName);
    }

    if (trip.participationConfirmed.length === trip.members.length) {
        trip.pendingActions = [];
        trip.missingItem = '';
    }
    
    saveTripsToStorage();
    render();
  } else {
    if (confirm('Are you sure you want to leave this trip? You will be removed from the group and won\'t have access to the plans.')) {
      
      trip.members = trip.members.filter(m => m !== currentUserName);
      if (trip.acceptedMembers) {
          trip.acceptedMembers = trip.acceptedMembers.filter(m => m !== currentUserName);
      }
      if (trip.participationConfirmed) {
          trip.participationConfirmed = trip.participationConfirmed.filter(m => m !== currentUserName);
      }
      
      const action = trip.pendingActions.find(a => a.title === 'Confirm presence');
      if (action && action.targetUsers) {
        action.targetUsers = action.targetUsers.filter(u => u !== currentUserName);
      }
      
      if (trip.participationConfirmed && trip.participationConfirmed.length === trip.members.length) {
          trip.pendingActions = [];
          trip.missingItem = '';
      }

      if (!trip.toastEvents) trip.toastEvents = [];
      trip.toastEvents.push({
        id: Date.now(),
        type: 'MEMBER_LEFT',
        message: `${currentUserName} left the trip "${trip.name}".`,
        targetUsers: trip.members.filter(m => m !== currentUserName), 
        notifiedUsers: []
      });
      
      saveTripsToStorage();
      setScreen('home');
      render();
    }
  }
}

function resetVotes() {
  const trip = getCurrentTrip();
  if (!trip) return;
  trip.currentSuggestionIndex = 0;
  trip.likedSuggestions = [];
  trip.votesConfirmed = false;
  saveTripsToStorage();
  renderSuggestions();
}

function toBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}

function renderProfile() {
  const totalTrips = state.trips.length;
  const uniqueMembers = new Set(state.trips.flatMap((trip) => trip.members));
  const totalExpenses = state.trips.reduce((sum, trip) => sum + trip.expenses.length, 0);
  const profileName = state.currentUser?.name || 'Utilizador';
  const profileEmail = state.currentUser?.email || 'Trip organiser';

  if($('#profile-user-name')) $('#profile-user-name').textContent = profileName;
  if($('#profile-user-role')) $('#profile-user-role').textContent = profileEmail;
  if($('#profile-avatar')) $('#profile-avatar').textContent = initials(profileName || 'U');
  if($('#profile-stat-trips')) $('#profile-stat-trips').textContent = totalTrips;
  if($('#profile-stat-members')) $('#profile-stat-members').textContent = uniqueMembers.size;
  if($('#profile-stat-expenses')) $('#profile-stat-expenses').textContent = totalExpenses;
}

function renderExpensePayerOptions() {
  const trip = getCurrentTrip();
  if (!trip) return;
  
  const select = $('#expense-payer');
  if(select) select.innerHTML = trip.members.map((member) => `<option value="${member}">${member}</option>`).join('');
  
  const membersContainer = $('#expense-custom-members');
  if (membersContainer) {
    membersContainer.innerHTML = trip.members.map(member => `
      <label class="checkbox-item">
        <input type="checkbox" value="${member}" checked>
        ${member}
      </label>
    `).join('');
  }
}

function renderChrome() {
  if($('.app-shell')) $('.app-shell').classList.toggle('auth-mode', !state.authenticated);
  if($('.bottom-nav')) $('.bottom-nav').classList.toggle('hidden-nav', !state.authenticated);
}

function render() {
  renderChrome();
  renderAuth();
  renderHome();
  renderTrips();
  renderTripDetail();
  renderSuggestions();
  renderProfile();
  renderExpensePayerOptions();
  if($('#duration-value')) $('#duration-value').textContent = state.createTripDuration;

  checkAndShowVotingDoneNotification();
  checkAndShowToastEvents();
}

function openTrip(id) {
  state.currentTripId = id;
  const trip = state.trips.find((t) => t.id === id);
  if (trip && trip.votesConfirmed) {
    state.detailTab = 'itinerary';
  } else {
    state.detailTab = 'votes';
  }
  setScreen('trip-detail');
}

function openTripFromAction(id) { openTrip(id); }

function markExpensePaid(tripId, expenseId) {
  const trip = state.trips.find((item) => item.id === tripId);
  const expense = trip?.expenses.find((item) => item.id === expenseId);
  const currentUserName = state.currentUser?.name || 'Alguém';

  if (expense) { 
    expense.pending = false; 
    expense.owedAmount = 0; 

    if (!trip.toastEvents) trip.toastEvents = [];
    
    let targets = [];

    if (expense.paidBy && expense.paidBy !== currentUserName) {
      targets.push(expense.paidBy);
    }

    if (expense.participantNames) {
      expense.participantNames.forEach(m => {
        if (m !== currentUserName && !targets.includes(m)) {
          targets.push(m);
        }
      });
    }

    trip.toastEvents.push({
      id: Date.now(),
      type: 'EXPENSE_PAID',
      message: ` ${currentUserName} has paid for "${expense.title}". The matter is resolved! ✅`,
      targetUsers: targets,
      notifiedUsers: []
    });
  }

  if (trip && trip.pendingActions) {
    trip.pendingActions = trip.pendingActions.filter(action => !(action.type === 'expense' && action.expenseId === expenseId));
  }

  saveTripsToStorage();
  render();
}

function removeMember(memberName) {
  const trip = getCurrentTrip();
  if (!trip) return;
  
  const currentUserName = state.currentUser?.name || 'You';

  if (memberName.trim().toLowerCase() !== currentUserName.trim().toLowerCase()) {
    alert("Security error: You can only remove yourself from this trip.");
    return;
  }
  
  if (confirm('Are you sure you want to leave this trip? You will lose access to it.')) {
    trip.members = trip.members.filter((m) => m.trim().toLowerCase() !== currentUserName.trim().toLowerCase());
    if (trip.acceptedMembers) {
        trip.acceptedMembers = trip.acceptedMembers.filter((m) => m.trim().toLowerCase() !== currentUserName.trim().toLowerCase());
    }
    
    if (trip.pendingActions) {
      trip.pendingActions.forEach(action => {
        if (action.targetUsers) {
          action.targetUsers = action.targetUsers.filter(u => u.trim().toLowerCase() !== currentUserName.trim().toLowerCase());
        }
      });
    }
    
    if (!trip.toastEvents) trip.toastEvents = [];
    trip.toastEvents.push({
      id: Date.now(),
      type: 'MEMBER_LEFT',
      message: ` ${currentUserName} left the trip "${trip.name}".`,
      targetUsers: trip.members.filter(m => m !== currentUserName), 
      notifiedUsers: []
    });

    saveTripsToStorage();
    setScreen('home');
    render();
  }
}

function createTrip() {
  const nameInput = $('#trip-name');
  const destinationInput = $('#trip-destination');
  const budgetInput = $('#trip-budget');
  const startDateInput = $('#trip-start-date'); 
  const endDateInput = $('#trip-end-date');    

  const name = nameInput?.value.trim();
  const destination = destinationInput?.value.trim();
  const budget = Number(budgetInput?.value || 0);
  const startVal = startDateInput?.value;    
  const endVal = endDateInput?.value;        
  
  const myName = state.currentUser ? state.currentUser.name : 'You';
  const checkedBoxes = $$('#trip-contact-list input[type="checkbox"]:checked');
  const members = [myName, ...checkedBoxes.map(cb => cb.value)];

  if (!name || !destination || !startVal || !endVal) { 
    alert('Please fill in the name, destination, and select the dates.'); 
    return; 
  }

  const startObj = new Date(startVal);
  const endObj = new Date(endVal);
  
  if (endObj < startObj) {
    alert('The end date cannot be before the start date.');
    return;
  }

  const months = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
  const formattedStart = `${startObj.getDate()} ${months[startObj.getMonth()]}`;
  const formattedEnd = `${endObj.getDate()} ${months[endObj.getMonth()]}`;

  const diffTime = Math.abs(endObj - startObj);
  const calculatedDuration = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1; 

  const vTotal = members.length; 
  const vCompleted = 1; 

  const invitedMembers = members.filter(m => m !== myName);

  const newTrip = {
    id: Date.now(),
    name: name,
    creator: myName, 
    destination: destination,
    city: 'TBD', 
    start: formattedStart,       
    end: formattedEnd,           
    durationDays: calculatedDuration, 
    budget: budget,
    status: 'planning',
    members: members,
    acceptedMembers: [myName],
    votesCompleted: vCompleted, 
    votesTotal: vTotal,
    votedMembers: [myName],
    currentSuggestionIndex: 0,
    likedSuggestions: [],
    missingItem: 'Destination',
    approvedActivities: [],
    itinerary: [{ 
      day: 1, 
      nowNextTitle: 'Planning', 
      items: ['Trip created. Waiting for members to join and vote.'] 
    }],
    expenses: [],
    pendingActions: [
      { 
        title: 'Trip Invitation', 
        description: `${myName} invited you to join ${name}.`, 
        cta: 'Respond',
        targetUsers: invitedMembers, 
        type: 'invite'
      },
      { 
        title: 'Voting in progress', 
        description: `Your vote is needed to decide the destination of ${name}.`, 
        cta: 'Go vote now',
        targetUsers: [], 
        type: 'vote'
      }
    ],
    suggestions: [
      { city: 'Amesterdão', subtitle: 'Canais icónicos e vida vibrante', avgCost: 425, emoji: '🌷', image: 'amsterdao.jpg', rating: 4.8, tags: ['Cultura', 'Noite'], votes: 1 },
      { city: 'Roterdão', subtitle: 'Arquitetura futurista e porto histórico', avgCost: 185, emoji: '🏗️', image: 'roterdao.jpg', rating: 4.5, tags: ['Design', 'Moderna'], votes: 0 },
      { city: 'Utrecht', subtitle: 'Canais charmosos e vibe universitária', avgCost: 230, emoji: '🚲', image: 'utrecht.jpg', rating: 4.6, tags: ['História', 'Relax'], votes: 0 }
    ],
    voteResults: { destination: 'TBD', accommodation: 'TBD' }
  };

  state.trips.unshift(newTrip);
  saveTripsToStorage();
  state.currentTripId = newTrip.id;
  
  closeModal('modal-create-trip');

  if(nameInput) nameInput.value = '';
  if(destinationInput) destinationInput.value = '';
  if(budgetInput) budgetInput.value = '';
  if(startDateInput) startDateInput.value = ''; 
  if(endDateInput) endDateInput.value = '';     
  
  $$('#trip-contact-list input[type="checkbox"]').forEach(cb => {
    if (['Tomás', 'Sofia', 'Nuno'].includes(cb.value)) cb.checked = true;
    else cb.checked = false;
  });
  
  render();
  setScreen('home'); 

  showTripNotification(invitedMembers);
}

function saveExpense() {
  const trip = getCurrentTrip();
  if (!trip) return;
  
  const description = $('#expense-description')?.value.trim();
  const total = Number($('#expense-total')?.value || 0);
  const splitType = $('#expense-split')?.value;
  const payer = $('#expense-payer')?.value || 'Eu';

  if (!description || !total) {
    alert('Please fill in description and amount.');
    return;
  }

  let participantsCount = trip.members.length;
  let participantNames = [...trip.members];

  if (splitType === 'custom') {
    const checkedBoxes = $$('#expense-custom-members input[type="checkbox"]:checked');
    participantsCount = checkedBoxes.length;
    if (participantsCount === 0) {
      alert('Selecione pelo menos um participante para dividir a despesa.');
      return;
    }
    participantNames = checkedBoxes.map(cb => cb.value); 
  }

  const owedPerPerson = total / participantsCount;
  const expenseId = Date.now();

  trip.expenses.unshift({
    id: expenseId,
    title: description,
    amount: total,
    paidBy: payer,
    owedPerPerson: owedPerPerson,
    participants: participantsCount,
    participantNames: participantNames,
    pending: true
  });

  const debtors = participantNames.filter(name => name.trim().toLowerCase() !== payer.trim().toLowerCase());
  if (debtors.length > 0) {
    if (!trip.pendingActions) trip.pendingActions = [];
    trip.pendingActions.push({
      title: 'Pending Expense',
      description: `${payer} added "${description}". You owe €${owedPerPerson.toFixed(2)}.`,
      cta: 'Go to trip',
      targetUsers: debtors, 
      type: 'expense',
      expenseId: expenseId
    });

    if (!trip.toastEvents) trip.toastEvents = [];
    trip.toastEvents.push({
      id: Date.now(),
      type: 'NEW_EXPENSE',
      message: `New €${owedPerPerson.toFixed(2)} to ${payer} for "${description}".`,
      targetUsers: debtors, 
      notifiedUsers: []
    });
  }

  if ($('#expense-description')) $('#expense-description').value = '';
  if ($('#expense-total')) $('#expense-total').value = '';
  if ($('#expense-split')) $('#expense-split').value = 'all';

  closeModal('modal-add-expense');
  saveTripsToStorage();
  render();
}

async function saveSuggestion() {
  const trip = getCurrentTrip();
  if (!trip) return;

  const city = $('#suggestion-city').value.trim();
  const subtitle = $('#suggestion-subtitle').value.trim();
  const avgCost = Number($('#suggestion-cost').value || 0);
  const file = $('#suggestion-image').files[0];

  if (!city || !subtitle || !avgCost || !file) {
    alert('Fill all fields and select an image.');
    return;
  }

  const imageBase64 = await toBase64(file);
  trip.suggestions.push({
    city, subtitle, avgCost, image: imageBase64, suggestedBy: 'Utilizador', votes: 0
  });

  $('#suggestion-city').value = '';
  $('#suggestion-subtitle').value = '';
  $('#suggestion-cost').value = '';
  $('#suggestion-image').value = '';

  closeModal('modal-add-suggestion');
  state.suggestionIndexByTrip[trip.id] = trip.suggestions.length - 1;
  renderSuggestions();
}

function deleteTrip(event, id) {
  event.stopPropagation();
  if (confirm('Are you sure you want to delete this trip?')) {
    state.trips = state.trips.filter(trip => trip.id !== id);
    saveTripsToStorage();
    render();
  }
}

function closeCurrentTrip() {
  const trip = getCurrentTrip();
  if (!trip) return;

  if (trip.status === 'closed') {
    alert('This trip is already closed.');
    return;
  }

  const currentUserName = state.currentUser?.name || 'You';

  if (confirm(`Do you want to finish and close "${trip.name}"?`)) {
    trip.status = 'closed';
    trip.itinerary = [{ 
      day: 'Final', 
      nowNextTitle: 'Trip completed', 
      items: ['✅ Trip archived successfully.', 'Check your final balances in the Expenses tab.'] 
    }];
    trip.pendingActions = [];

    // Adiciona o evento de notificação em inglês para os outros membros
    if (!trip.toastEvents) trip.toastEvents = [];
    trip.toastEvents.push({
      id: Date.now(),
      type: 'TRIP_CLOSED',
      message: 'The trip has come to an end',
      targetUsers: trip.members.filter(m => m !== currentUserName),
      notifiedUsers: []
    });

    saveTripsToStorage();
    
    // Altera o filtro ativo para a aba de histórico
    state.tripsFilter = 'closed'; 
    
    render();
    setScreen('trips');
    
    // Sincroniza visualmente a classe 'active' nos botões de filtro do ecrã de viagens
    document.querySelectorAll('[data-trip-filter]').forEach(btn => {
      btn.classList.toggle('active', btn.dataset.tripFilter === 'closed');
    });
  }
}

function saveMember() {
  const trip = getCurrentTrip();
  if (!trip) return;
  const includePrevious = $('#include-previous-expenses')?.value === 'yes';
  
  const checkedBoxes = $$('#add-member-contact-list input[type="checkbox"]:checked');
  const selectedNames = checkedBoxes.map(cb => cb.value);
  
  if (selectedNames.length === 0) { 
    alert('Please select at least one member to invite.'); 
    return; 
  }
  
  selectedNames.forEach(name => {
    if (!trip.members.includes(name)) {
      trip.members.push(name);
      
      let inviteAction = trip.pendingActions.find(a => a.type === 'invite');
      if (!inviteAction) {
        inviteAction = { 
          title: 'Trip Invitation', 
          description: `${state.currentUser?.name || 'Someone'} invited you to join ${trip.name}.`, 
          cta: 'Respond',
          targetUsers: [],
          type: 'invite'
        };
        trip.pendingActions.push(inviteAction);
      }
      if (!inviteAction.targetUsers.includes(name)) {
        inviteAction.targetUsers.push(name);
      }
    }
  });
  
  if (includePrevious) {
    trip.expenses.forEach((expense) => { 
      expense.participants += selectedNames.length; 
      if (expense.pending) expense.owedAmount = Number(expense.amount) / expense.participants; 
    });
  }

  if (!trip.toastEvents) trip.toastEvents = [];
  trip.toastEvents.push({
    id: Date.now(),
    type: 'MEMBER_JOINED',
    message: `${selectedNames.join(' and ')} was invited to the trip "${trip.name}".`,
    targetUsers: trip.members.filter(m => !selectedNames.includes(m)),
    notifiedUsers: []
  });
  
  if($('#include-previous-expenses')) $('#include-previous-expenses').value = 'no';
  closeModal('modal-add-member');
  
  saveTripsToStorage(); 
  render();
  
  showActionToast(`Invite sent to ${selectedNames.join(', ')}`); 
}

function saveTripsToStorage() {
  writeJSON('swipetravel.trips', state.trips);
}

document.addEventListener('click', (event) => {
  const nav = event.target.closest('[data-nav]');
  if (nav) setScreen(nav.dataset.nav);
  const back = event.target.closest('[data-back]');
  if (back) setScreen(back.dataset.back);
  const closeBtn = event.target.closest('[data-close-modal]');
  if (closeBtn) closeModal(closeBtn.dataset.closeModal);
  const detailTab = event.target.closest('[data-detail-tab]');
  if (detailTab) { state.detailTab = detailTab.dataset.detailTab; renderTripDetail(); }
  const tripFilter = event.target.closest('[data-trip-filter]');
  if (tripFilter) { state.tripsFilter = tripFilter.dataset.tripFilter; renderTrips(); }
  const authModeBtn = event.target.closest('[data-auth-mode]');
  if (authModeBtn) setAuthMode(authModeBtn.dataset.authMode);
});

safeListen('#go-profile', 'click', () => setScreen('profile'));
safeListen('#open-create-trip', 'click', () => openModal('modal-create-trip'));
safeListen('#save-trip-btn', 'click', createTrip);
safeListen('#create-trip-submit', 'click', createTrip);
safeListen('#duration-minus', 'click', () => { state.createTripDuration = Math.max(1, state.createTripDuration - 1); render(); });
safeListen('#duration-plus', 'click', () => { state.createTripDuration += 1; render(); });
safeListen('#open-suggestions', 'click', () => setScreen('suggestions'));
safeListen('#open-add-expense-inline', 'click', () => openModal('modal-add-expense'));
safeListen('#save-expense-btn', 'click', saveExpense);
safeListen('#open-add-member', 'click', () => {
  renderAvailableMembersToInvite();
  openModal('modal-add-member');
});

function renderAvailableMembersToInvite() {
  const trip = getCurrentTrip();
  if (!trip) return;

  const allContacts = ['João', 'Ricardo', 'Tomás', 'Sofia', 'Nuno', 'Leonor'];
  
  const currentMembersLower = trip.members.map(m => m.trim().toLowerCase());
  const available = allContacts.filter(name => !currentMembersLower.includes(name.trim().toLowerCase()));

  const container = $('#add-member-contact-list');
  if (container) {
    if (available.length === 0) {
      container.innerHTML = '<div class="empty-state" style="font-size:0.85rem;">All contacts are already in this trip!</div>';
    } else {
      container.innerHTML = available.map(name => `
        <label class="checkbox-item">
          <input type="checkbox" value="${name}"> ${name}
        </label>
      `).join('');
    }
  }
}
safeListen('#save-member-btn', 'click', saveMember);
safeListen('#expense-split', 'change', (event) => {
  const customGroup = $('#expense-custom-count-group');
  if(customGroup) customGroup.classList.toggle('hidden', event.target.value !== 'custom');
});
safeListen('#login-submit', 'click', login);
safeListen('#register-submit', 'click', register);
safeListen('#logout-btn', 'click', logout);
safeListen('#open-add-suggestion', 'click', () => openModal('modal-add-suggestion'));
safeListen('#save-suggestion-btn', 'click', saveSuggestion);
safeListen('#trip-finish-btn', 'click', closeCurrentTrip);

window.addEventListener('storage', (event) => {
  if (event.key === 'swipetravel.trips') {
    state.trips = readJSON('swipetravel.trips', []);
    if (state.screen === 'trip-detail' && state.currentTripId) {
      const tripAberta = state.trips.find(t => t.id === state.currentTripId);
      
      if (tripAberta && tripAberta.status === 'closed') {
        state.tripsFilter = 'closed';
        document.querySelectorAll('[data-trip-filter]').forEach(btn => {
          btn.classList.toggle('active', btn.dataset.tripFilter === 'closed');
        });
        setScreen('trips');
      }
    }
    
    render(); 
  }
});

seedDemoAccount();
loadSession();
render();
setScreen(state.screen);

window.openTrip = openTrip;
window.openTripFromAction = openTripFromAction;
window.markExpensePaid = markExpensePaid;
window.removeMember = removeMember;
window.swipeSuggestion = swipeSuggestion;
window.deleteTrip = deleteTrip;
window.confirmVotes = confirmVotes;
window.resetVotes = resetVotes;
window.setScreen = setScreen;
window.confirmParticipation = confirmParticipation;
window.respondToInvite = respondToInvite;
