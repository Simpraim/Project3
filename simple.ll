(DATA  x)
(FUNCTION  add  [(int a) (int b)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 0)]  [(r 0)])
    (OPER 5 Mov [(r 0)]  [(r 0)])
    (OPER 6 Add_I [(r 3)]  [(r 1)(r 2)])
    (OPER 7 Mov [(m RetReg)]  [(r 3)])
    (OPER 8 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
