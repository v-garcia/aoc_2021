open Day_two_input

let rec navigate = function
       | [], pos -> [], pos
       | n::ns, (x, y) -> match n with 
                          | Forward x' -> navigate (ns, (x + x', y))
                          | Up y' -> navigate (ns, (x, y - y'))
                          | Down y' -> navigate (ns, (x, y + y'))
let res_1 = let (_, (x, y)) = navigate (Day_two_input.command_list, (0, 0)) in x * y

let rec navigate' = function
       | [], t -> [], t
       | n::ns, (x, y, z) -> match n with 
                                   | Down v -> navigate' (ns, (x, y, (z + v)))
                                   | Up v -> navigate' (ns, (x, y, (z - v)))
                                   | Forward v -> navigate' (ns, ((x + v), (y + (z * v)), z))

                                   
let res_2 = let (_, (x, y,_)) = navigate' (Day_two_input.command_list, (0, 0, 0)) in x * y