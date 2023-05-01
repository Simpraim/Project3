(DATA  x)
(FUNCTION  a  [(int s) (int t)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 0)]  [(r 0)])
    (OPER 5 Mov [(r 0)]  [(r 0)])
  )
  (BB 4
    (OPER 6 Mov [(r 5)]  [(i 1)])
    (OPER 7 LT [(r 6)]  [(r 4)(r 5)])
    (OPER 8 BEQ []  [(r 6)(i 0)(bb 6)])
  )
  (BB 5
    (OPER 9 Mov [(r 7)]  [(i 1)])
    (OPER 10 Mov [(m RetReg)]  [(r 7)])
    (OPER 11 Jmp []  [(bb 1)])
  )
  (BB 7
  )
  (BB 8
    (OPER 14 Mov [(r 8)]  [(i 10)])
    (OPER 15 LT [(r 9)]  [(r 4)(r 8)])
    (OPER 16 BEQ []  [(r 9)(i 0)(bb 10)])
  )
  (BB 9
    (OPER 17 Mov [(r 0)]  [(r 0)])
    (OPER 18 Jmp []  [(bb 8)])
  )
  (BB 10
    (OPER 19 Add_I [(r 10)]  [(r 1)(r 2)])
    (OPER 20 Mov [(m RetReg)]  [(r 10)])
    (OPER 21 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
  (BB 6
    (OPER 12 Mov [(r 0)]  [(r 0)])
    (OPER 13 Jmp []  [(bb 7)])
  )
)
(FUNCTION  b  [(int l) (int r)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Add_I [(r 3)]  [(r 1)(r 2)])
    (OPER 5 Mov [(m RetReg)]  [(r 3)])
    (OPER 6 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 1)]  [(i 1)])
    (OPER 5 Mov [(m RetReg)]  [(r 1)])
    (OPER 6 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
