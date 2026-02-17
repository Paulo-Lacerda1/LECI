%% 1a

P_D = 0.01;              % has Disease
P_notD = 1 - P_D;        % not has disease
P_Pos_given_D = 0.99;    % test is positive given disease
P_Pos_given_notD = 0.05; % test is positive given not disease

% prob of testing positive
P_Pos = P_Pos_given_D * P_D + P_Pos_given_notD * P_notD;

% conditional probability : prob of disease knowing that you're positive 
P_D_given_Pos = (P_Pos_given_D * P_D) / P_Pos


%% 1b 

N = 1e5;         % people
has_disease = rand(N,1) < P_D; % 0 if no disease, 1 if disease

test_positive = false(N,1); % count where test is positive (> 0)
test_positive(has_disease) = rand(sum(has_disease),1) < P_Pos_given_D;
test_positive(~has_disease) = rand(sum(1-has_disease),1) < P_Pos_given_notD;

n_positive = sum(test_positive);
n_disease_given_positive = sum(has_disease & test_positive);
simulated_probability = n_disease_given_positive / n_positive
