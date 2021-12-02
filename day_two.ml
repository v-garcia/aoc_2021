open Day_two_input

let rec navigate = function
       | [], pos -> [], pos
       | n::ns, (x, y) -> match n with 
                          | Forward x' -> navigate (ns, (x + x', y))
                          | Up y' -> navigate (ns, (x, y - y'))
                          | Down y' -> navigate (ns, (x, y + y'))
let res_1 = let (_, (x, y)) = navigate (Day_two_input.command_list, (0, 0)) in x * y