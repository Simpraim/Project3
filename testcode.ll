(DATA  a)
(FUNCTION  addThem  [(int d) (int e)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 0)]  [(r 0)])
    (OPER 5 Mov [(m RetReg)]  [(r 3)])
    (OPER 6 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
(FUNCTION  putDigit  [(int s)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 2)]  [(i 48)])
    (OPER 5 Add_I [(r 3)]  [(r 2)(r 1)])
    (OPER 6 Pass []  [(r 3)])
    (OPER 7 JSR []  [(s putchar)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
(FUNCTION  printInt  [(int r)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 0)]  [(r 0)])
  )
  (BB 4
    (OPER 5 Mov [(r 4)]  [(i 10000)])
    (OPER 6 GTE [(r 5)]  [(r 1)(r 4)])
    (OPER 7 BEQ []  [(r 5)(i 0)(bb 6)])
  )
  (BB 5
    (OPER 8 Mov [(r 6)]  [(i 45)])
    (OPER 9 Pass []  [(r 6)])
    (OPER 10 JSR []  [(s putchar)])
    (OPER 11 Mov [(r 7)]  [(i 1)])
    (OPER 12 Pass []  [(r 7)])
    (OPER 13 JSR []  [(s putDigit)])
    (OPER 14 Jmp []  [(bb 1)])
  )
  (BB 7
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
  (BB 14
  )
  (BB 16
    (OPER 31 Mov [(r 12)]  [(i 1)])
    (OPER 32 EQ [(r 13)]  [(r 3)(r 12)])
    (OPER 33 BEQ []  [(r 13)(i 0)(bb 19)])
  )
  (BB 17
    (OPER 34 Mov [(r 14)]  [(i 0)])
    (OPER 35 Pass []  [(r 14)])
    (OPER 36 JSR []  [(s putDigit)])
  )
  (BB 19
    (OPER 37 Jmp []  [(bb 15)])
  )
  (BB 22
  )
  (BB 24
    (OPER 45 Mov [(r 17)]  [(i 1)])
    (OPER 46 EQ [(r 18)]  [(r 3)(r 17)])
    (OPER 47 BEQ []  [(r 18)(i 0)(bb 27)])
  )
  (BB 25
    (OPER 48 Mov [(r 19)]  [(i 0)])
    (OPER 49 Pass []  [(r 19)])
    (OPER 50 JSR []  [(s putDigit)])
  )
  (BB 27
    (OPER 51 Jmp []  [(bb 23)])
  )
  (BB 6
  )
  (BB 8
    (OPER 15 Mov [(r 8)]  [(i 1000)])
    (OPER 16 GTE [(r 9)]  [(r 1)(r 8)])
    (OPER 17 BEQ []  [(r 9)(i 0)(bb 11)])
  )
  (BB 9
    (OPER 18 Mov [(r 0)]  [(r 0)])
    (OPER 19 Pass []  [(r 2)])
    (OPER 20 JSR []  [(s putDigit)])
    (OPER 21 Mov [(r 0)]  [(r 0)])
    (OPER 22 Mov [(r 0)]  [(r 0)])
  )
  (BB 11
  )
  (BB 12
    (OPER 23 Mov [(r 10)]  [(i 100)])
    (OPER 24 GTE [(r 11)]  [(r 1)(r 10)])
    (OPER 25 BEQ []  [(r 11)(i 0)(bb 14)])
  )
  (BB 13
    (OPER 26 Mov [(r 0)]  [(r 0)])
    (OPER 27 Pass []  [(r 2)])
    (OPER 28 JSR []  [(s putDigit)])
    (OPER 29 Mov [(r 0)]  [(r 0)])
    (OPER 30 Mov [(r 0)]  [(r 0)])
  )
  (BB 15
  )
  (BB 20
    (OPER 38 Mov [(r 15)]  [(i 10)])
    (OPER 39 GTE [(r 16)]  [(r 1)(r 15)])
    (OPER 40 BEQ []  [(r 16)(i 0)(bb 22)])
  )
  (BB 21
    (OPER 41 Mov [(r 0)]  [(r 0)])
    (OPER 42 Pass []  [(r 2)])
    (OPER 43 JSR []  [(s putDigit)])
    (OPER 44 Mov [(r 0)]  [(r 0)])
  )
  (BB 23
    (OPER 52 Pass []  [(r 1)])
    (OPER 53 JSR []  [(s putDigit)])
    (OPER 54 Jmp []  [(bb 7)])
  )
)
(FUNCTION  main  [(void void)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 0)]  [(r 0)])
  )
  (BB 4
    (OPER 5 Mov [(r 6)]  [(i 5)])
    (OPER 6 EQ [(r 7)]  [(r 1)(r 6)])
    (OPER 7 BEQ []  [(r 7)(i 0)(bb 6)])
  )
  (BB 5
    (OPER 8 Store []  [(r 0)(s a)])
  )
  (BB 7
    (OPER 11 Mov [(r 0)]  [(r 0)])
    (OPER 12 Mov [(r 0)]  [(r 0)])
  )
  (BB 8
    (OPER 13 Mov [(r 8)]  [(i 8)])
    (OPER 14 LTE [(r 9)]  [(r 5)(r 8)])
    (OPER 15 BEQ []  [(r 9)(i 0)(bb 10)])
  )
  (BB 9
    (OPER 16 Mov [(r 0)]  [(r 0)])
    (OPER 17 Mov [(r 0)]  [(r 0)])
    (OPER 18 Jmp []  [(bb 8)])
  )
  (BB 10
    (OPER 19 Mov [(r 0)]  [(r 0)])
    (OPER 20 Mov [(r 0)]  [(r 0)])
    (OPER 21 Mov [(r 0)]  [(r 0)])
    (OPER 22 Mov [(r 10)]  [(i 56)])
    (OPER 23 Pass []  [(r 10)])
    (OPER 24 JSR []  [(s putchar)])
    (OPER 25 Mov [(r 11)]  [(i 61)])
    (OPER 26 Pass []  [(r 11)])
    (OPER 27 JSR []  [(s putchar)])
    (OPER 28 Add_I [(r 12)]  [(r 2)(r 3)])
    (OPER 29 Pass []  [(r 12)])
    (OPER 30 JSR []  [(s putchar)])
    (OPER 31 Mov [(r 13)]  [(i 10)])
    (OPER 32 Pass []  [(r 13)])
    (OPER 33 JSR []  [(s putchar)])
    (OPER 34 Mov [(r 0)]  [(r 0)])
  )
  (BB 11
    (OPER 35 Mov [(r 14)]  [(i 10)])
    (OPER 36 LT [(r 15)]  [(r 5)(r 14)])
    (OPER 37 BEQ []  [(r 15)(i 0)(bb 13)])
  )
  (BB 12
    (OPER 38 Mov [(r 16)]  [(i 48)])
    (OPER 39 Add_I [(r 17)]  [(r 16)(r 5)])
    (OPER 40 Pass []  [(r 17)])
    (OPER 41 JSR []  [(s putchar)])
    (OPER 42 Mov [(r 0)]  [(r 0)])
    (OPER 43 Jmp []  [(bb 11)])
  )
  (BB 13
    (OPER 44 Mov [(r 18)]  [(i 10)])
    (OPER 45 Pass []  [(r 18)])
    (OPER 46 JSR []  [(s putchar)])
    (OPER 47 Mov [(r 19)]  [(i 67)])
    (OPER 48 Pass []  [(r 19)])
    (OPER 49 JSR []  [(s putchar)])
    (OPER 50 Mov [(r 20)]  [(i 83)])
    (OPER 51 Pass []  [(r 20)])
    (OPER 52 JSR []  [(s putchar)])
    (OPER 53 Mov [(r 21)]  [(i 3510)])
    (OPER 54 Pass []  [(r 21)])
    (OPER 55 JSR []  [(s printInt)])
    (OPER 56 Mov [(r 22)]  [(i 10)])
    (OPER 57 Pass []  [(r 22)])
    (OPER 58 JSR []  [(s putchar)])
    (OPER 59 Mov [(r 0)]  [(r 0)])
    (OPER 60 Mov [(r 0)]  [(r 0)])
    (OPER 61 Mov [(r 0)]  [(r 0)])
    (OPER 62 Mov [(r 0)]  [(r 0)])
    (OPER 63 Mov [(r 0)]  [(r 0)])
  )
  (BB 14
    (OPER 64 Mov [(r 23)]  [(i 0)])
    (OPER 65 EQ [(r 24)]  [(r 1)(r 23)])
    (OPER 66 BEQ []  [(r 24)(i 0)(bb 16)])
  )
  (BB 15
  )
  (BB 18
    (OPER 67 Mov [(r 25)]  [(i 0)])
    (OPER 68 EQ [(r 26)]  [(r 2)(r 25)])
    (OPER 69 BEQ []  [(r 26)(i 0)(bb 20)])
  )
  (BB 19
    (OPER 70 Mov [(r 0)]  [(r 0)])
  )
  (BB 21
  )
  (BB 17
  )
  (BB 30
    (OPER 85 Mov [(r 31)]  [(i 10)])
    (OPER 86 EQ [(r 32)]  [(r 5)(r 31)])
    (OPER 87 BEQ []  [(r 32)(i 0)(bb 32)])
  )
  (BB 31
    (OPER 88 Mov [(r 33)]  [(i 99)])
    (OPER 89 Pass []  [(r 33)])
    (OPER 90 JSR []  [(s putchar)])
    (OPER 91 Mov [(r 34)]  [(i 0)])
    (OPER 92 Pass []  [(r 34)])
    (OPER 93 JSR []  [(s putDigit)])
    (OPER 94 Mov [(r 35)]  [(i 0)])
    (OPER 95 Pass []  [(r 35)])
    (OPER 96 JSR []  [(s putDigit)])
    (OPER 97 Mov [(r 36)]  [(i 108)])
    (OPER 98 Pass []  [(r 36)])
    (OPER 99 JSR []  [(s putchar)])
  )
  (BB 33
    (OPER 115 Mov [(r 41)]  [(i 10)])
    (OPER 116 Pass []  [(r 41)])
    (OPER 117 JSR []  [(s putchar)])
    (OPER 118 Mov [(r 42)]  [(i 0)])
    (OPER 119 Mov [(m RetReg)]  [(r 42)])
    (OPER 120 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
  (BB 6
    (OPER 9 Store []  [(r 0)(s a)])
    (OPER 10 Jmp []  [(bb 7)])
  )
  (BB 28
    (OPER 79 Mov [(r 0)]  [(r 0)])
    (OPER 80 Jmp []  [(bb 29)])
  )
  (BB 24
  )
  (BB 26
    (OPER 75 Mov [(r 29)]  [(i 0)])
    (OPER 76 EQ [(r 30)]  [(r 4)(r 29)])
    (OPER 77 BEQ []  [(r 30)(i 0)(bb 28)])
  )
  (BB 27
    (OPER 78 Mov [(r 0)]  [(r 0)])
  )
  (BB 29
    (OPER 81 Jmp []  [(bb 25)])
  )
  (BB 20
  )
  (BB 22
    (OPER 71 Mov [(r 27)]  [(i 0)])
    (OPER 72 EQ [(r 28)]  [(r 3)(r 27)])
    (OPER 73 BEQ []  [(r 28)(i 0)(bb 24)])
  )
  (BB 23
    (OPER 74 Mov [(r 0)]  [(r 0)])
  )
  (BB 25
    (OPER 82 Jmp []  [(bb 21)])
  )
  (BB 16
    (OPER 83 Mov [(r 0)]  [(r 0)])
    (OPER 84 Jmp []  [(bb 17)])
  )
  (BB 32
    (OPER 100 Mov [(r 37)]  [(i 98)])
    (OPER 101 Pass []  [(r 37)])
    (OPER 102 JSR []  [(s putchar)])
    (OPER 103 Mov [(r 38)]  [(i 97)])
    (OPER 104 Pass []  [(r 38)])
    (OPER 105 JSR []  [(s putchar)])
    (OPER 106 Mov [(r 39)]  [(i 100)])
    (OPER 107 Pass []  [(r 39)])
    (OPER 108 JSR []  [(s putchar)])
    (OPER 109 Mov [(r 40)]  [(i 61)])
    (OPER 110 Pass []  [(r 40)])
    (OPER 111 JSR []  [(s putchar)])
    (OPER 112 Pass []  [(r 5)])
    (OPER 113 JSR []  [(s printInt)])
    (OPER 114 Jmp []  [(bb 33)])
  )
)
