%% 2a

N = 1e5;         
n_bits = 20;             
p_error = 0.1;       

errors = rand(N, n_bits) < p_error;

% sum the errors
X = sum(errors, 2);

%% 2b

meanX = mean(X)
varX = var(X)

%% 2c

% theoretical values of the mean and the variance
tMean = n_bits * p_error
tVar = n_bits * p_error * (1 - p_error)

% pmf using binomial formula
k = 0:20;
pmf_X = 0:20;
for i = 1:length(k)
    pmf_X(i) = nchoosek(n_bits, k(i)) * p_error^k(i) * (1-p_error)^(n_bits-k(i));
end
pmf_X