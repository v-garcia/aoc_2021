#use "day_one_input.ml"

let rec count_deep_inc = function
  | a :: (b :: _ as t) ->
      if a < b then 1 + count_deep_inc t else count_deep_inc t
  | _ -> 0

let res_1 = count_deep_inc l

let rec count_deep_triple_inc = function
  | a :: b :: c :: d :: t ->
      if a + b + c < b + c + d then 1 + count_deep_triple_inc (b :: c :: d :: t)
      else count_deep_triple_inc (b :: c :: d :: t)
  | _ -> 0

let res_2 = count_deep_triple_inc l
