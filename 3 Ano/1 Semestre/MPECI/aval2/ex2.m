% Parameters
lambda = 500;       % requests/hour
mu = 6;             % 60 min / 10 min = 6 requests/hour
c = 100;            % Number of operators
u = lambda / mu;    % Traffic Intensity

%% A

% Calculate Average Queuing Time (Wq)
% Denominator is the "spare capacity" (Total Capacity - Arrival Rate)
spare_capacity = (c * mu) - lambda; 

Wq_hours = Pc / spare_capacity;
Wq_seconds = Wq_hours * 3600;

fprintf('Avg Queuing Time: %.4f seconds\n', Wq_seconds);

%% B
% Erlang-C Probability
sum_part = 0;
for k = 0:(c-1)
    sum_part = sum_part + (u^k)/factorial(k);
end

% Calculate the numerator term
term_c = (u^c)/factorial(c) * (c / (c - u));

% Probability of not waiting
Pc = term_c / (sum_part + term_c);
P_not_wait = 100 - Pc*100;
fprintf('Probability of not waiting (P_not_wait): %.4f %%\n', P_not_wait);

%% C
pmf = zeros(1, c-1);
for k = 0:(c-1)
    pmf(k+1) = (u^k / factorial(k)) / (sum_part + term_c);
end
% Plot the PMF
bar(0:(c-1), pmf);
xlabel('Number of Occupied Operators');
ylabel('Probability Mass Function (PMF)');
title('PMF of Occupied Operators');
grid on;

