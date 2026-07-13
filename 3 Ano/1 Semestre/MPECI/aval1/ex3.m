%% 3a

% States 0, 1, F
Table = [0.25 0.5  0;   % Trasition to 0
         0.25 0.25 0;   % Transition to 1
         0.5  0.25 1];  % Transition to F

%% 3b

p_0001 = Table(1,1)*Table(1,1)*Table(1,2)

%% 3c

p = 0;
for bit1 = [1,2]        
    for bit2 = [1,2]    
        for bit3 = [1,2]
            for bit4 = [1,2]
                for bit5 = [1,2]
                    p = p + Table(bit1, bit2) * Table(bit2, bit3) * Table(bit3, bit4) * Table(bit4, bit5);
                end
            end
        end
    end
end 

p