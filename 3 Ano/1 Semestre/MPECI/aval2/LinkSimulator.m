function [AM, PD] = LinkSimulator(lambda,B,C,F,N)
%
% [AM, PD] = LinkSimulator(lambda,B,C,F,N)
%
% Input:
%     lambda: packet arrival rate (in packets/second)
%     B:      av.g packet size (in Bytes)
%     C:      link capacity (in Mbps)
%     F:      queue capacity (in packets)
%     N:      stopping criterion (simulation finishes after N transmitted packets)
% Output:
%     AM:     average packet delay (in seconds)
%     PD:     packet drop probability (NEW)

    %Events:
    ARRIVAL= 0;       % Arrival of a packet            
    DEPARTURE= 1;     % Departure of a packet
    % Initialization of variables:
    Clock= 0;            % Simulation clock
    STATE = 0;           % 0 - connection is free; 1 - connection is occupied
    QUEUE_N= 0;          % No. of packets in queue
    DELAYS= 0;           % Sum of the delays of transmitted packets
    TRANSMITTED= 0;      % N. of transmitted packets
    % NEW VARIABLES
    DROPPED = 0;         % N. of packets dropped due to full queue
    TOTAL_ARRIVALS = 0;  % Total N. of arrivals processed
    QUEUE= [];           % Arriving time instant of each packet in the queue
    % Initializing the List of Events with the first ARRIVAL:
    tmp= Clock + exprnd(1/lambda);
    EventList = [ARRIVAL, tmp, tmp];
    
    while TRANSMITTED < N
        EventList= sortrows(EventList,2);  % sort EventList by time
        Event= EventList(1,1);             % register Event type in first row 
        Clock= EventList(1,2);             % update Clock with time instant of first row
        ArrInstant= EventList(1,3);        % register arrival instant (useful in DEPARTURE)
        EventList(1,:)= [];                % delete first row of EventList
        
        switch Event
            case ARRIVAL
                TOTAL_ARRIVALS = TOTAL_ARRIVALS + 1;
                tmp= Clock + exprnd(1/lambda);
                EventList = [EventList; ARRIVAL, tmp, tmp];
                if STATE == 0
                    STATE= 1;
                    % New packet size logic
                    r = rand();
                    if r < 0.25
                        PSize = B - 200;
                    elseif r < 0.75
                        PSize = B;
                    else
                        PSize = B + 200;
                    end
                    tTime= 8*PSize/(C*1e6);   % in seconds
                    EventList = [EventList; DEPARTURE, Clock + tTime, ArrInstant];
                else
                    if QUEUE_N < F
                        QUEUE_N= QUEUE_N + 1;
                        QUEUE= [QUEUE; ArrInstant];
                    else
                        % Count drop
                        DROPPED = DROPPED + 1;
                    end
                end
            case DEPARTURE
                DELAYS= DELAYS + (Clock - ArrInstant);
                TRANSMITTED= TRANSMITTED + 1;
                if QUEUE_N > 0
                    QInstant= QUEUE(1);
                    % Packet size logic
                    r = rand();
                    if r < 0.25
                        PSize = B - 200;
                    elseif r < 0.75
                        PSize = B;
                    else
                        PSize = B + 200;
                    end
                    tTime= 8*PSize/(C*1e6);   % in seconds
                    EventList = [EventList; DEPARTURE, Clock + tTime, QInstant];
                    QUEUE_N= QUEUE_N - 1;
                    QUEUE(1)= [];
                else
                    STATE= 0;
                end
        end
    end
    AM = DELAYS / TRANSMITTED;
    PD = DROPPED / TOTAL_ARRIVALS;
end