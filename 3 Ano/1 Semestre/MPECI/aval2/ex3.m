%% Parameters
lambda = 1400;      % Packet arrival rate (pps)
B = 1200;           % Average packet size (Bytes)
C = 10;             % Link capacity (Mbps)
F = 12;             % Queue capacity (packets)
N = 10000;          % Stop criterion (10^4 packets)
N_runs = 20;        % Number of simulation runs
alfa = 0.1;         % 90% Confidence Interval

% Store results
delays = zeros(1, N_runs);
drops = zeros(1, N_runs);

%% loop
fprintf('Running %d simulations...\n', N_runs);
for i = 1:N_runs
    % Call simulator
    [am_val, pd_val] = LinkSimulator(lambda, B, C, F, N);
    
    delays(i) = am_val;
    drops(i) = pd_val;
end

%% stats for APD
avg_delay = mean(delays);
std_delay = std(delays);
term_delay = norminv(1 - alfa/2) * (std_delay / sqrt(N_runs));

% Convert to milliseconds
avg_delay_ms = avg_delay * 1000;
term_delay_ms = term_delay * 1000;

%% stats for PDP
avg_drop = mean(drops);
std_drop = std(drops);
term_drop = norminv(1 - alfa/2) * (std_drop / sqrt(N_runs));

% Convert to Percentage
avg_drop_perc = avg_drop * 100;
term_drop_perc = term_drop * 100;

%% Display Results
fprintf('\n--- Simulation Results (90%% Confidence, %d runs) ---\n', N_runs);

fprintf('Avg Packet Delay (APD): %.4f +/- %.4f msec\n', ...
    avg_delay_ms, term_delay_ms);
fprintf('   Interval: [%.4f, %.4f] msec\n', ...
    avg_delay_ms - term_delay_ms, avg_delay_ms + term_delay_ms);

fprintf('Packet Drop Prob (PDP): %.4f +/- %.4f %%\n', ...
    avg_drop_perc, term_drop_perc);
fprintf('   Interval: [%.4f, %.4f] %%\n', ...
    avg_drop_perc - term_drop_perc, avg_drop_perc + term_drop_perc);
