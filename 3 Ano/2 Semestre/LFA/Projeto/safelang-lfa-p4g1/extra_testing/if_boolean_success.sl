# Test 1: Positive case for boolean logic and structural if-else

# Declare base variables
a: integer;
b: integer;
cond: bool;
msg: string;

a := 10;
b := 20;

# 1. Direct assignment of a boolean expression comparison
cond := a < b;

# 2. Basic if-then-end testing complex logical operators
if a <= b && b <> 0 then
    msg := "Valid comparison layout";
    writeln msg;
    c := a : integer;
end;

a := 1;
b := 1;
# 3. Full if-elseif-else block checking statement integration
if a > b then
    writeln "A is greater";
elseif a = b then
    writeln "They are completely equal";
else
    # Testing numeric and assignments within commonStat block
    a := a * 2;
    writeln "Fallback to else clause executed.";
end;

a := 0;
b := 5;

if (a = 0 && b = 5) then
    writeln "yeah";
end;

# 4. Mixing string and number types inside boolean expression rules securely
if "hello" <> "world" then
    if "hello" = "hello" then
        writeln "String matching rules are operational";
    end;
end;