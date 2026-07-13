# Expected TypeChecker error: nums[1] is integer, not boolean
nums := new list[integer] : list[integer];

1 >> nums;

if nums[1] then
   writeln "bad";
end;
