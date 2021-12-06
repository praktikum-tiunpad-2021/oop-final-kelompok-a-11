# Sudoku

This repository is a final project (Java GUI) from Object-Oriented Programming Class, Teknik Informatika Universitas Padjadjaran. 

[Challenge Guidelines](challenge-guideline.md)

Sudoku adalah game dengan tujuan untuk mengisikan angka-angka dari 1 sampai 9 ke dalam jaring-jaring 9×9 yang terdiri dari 9 kotak 3×3 tanpa ada angka yang berulang di satu baris, kolom atau kotak.

## Credits
| NPM           | Name                       |
| ------------- |----------------------------|
| 140810200019  | Muhammad Zidan Khairan     |
| 140810200027  | Harta Rama                 |
| 140810200037  | Azka Ghafara Putra Agung   |

## Change log
- **[Sprint Planning](changelog/sprint-planning.md) - (22 November 2021)** 
   - Mendiskusikan backlog dan mencari beberapa referensi untuk merancang proyek ini.

- **[Sprint 1](changelog/sprint-1.md) - (22 November 2021)** 
   - Inisialisasi Gradle
   - Inisialisasi JavaFX
   - Mencari Referensi Sudoku
   - Menambahkan Challenge Guideline

- **[Sprint 2](changelog/sprint-2.md) - (23 November 2021 x 29 November 2021)** 
   - Mengimplementasikan Algoritma Permainan Ke Dalam Java
   - Membuat Menu
   - Membuat Tingkat Kesulitan Permainan
   - Mendesain Tampilan
   - Membuat Beberapa Class Yang Akan Digunakan
   
- **[Sprint 3](changelog/sprint-3.md) - (30 November 2021 x 6 Desember 2021)** 
   - Short changes 1
   - Short changes 2

## Running The App

Main file `Sudoku.java` atau Sudoku kami dapat dijalankan dengan cara : 

 ### Menjalankan tanpa parameter 

 ``` 
 java Sudoku 
 ``` 

 Ketika dijalankan seperti ini, sudoku akan memuat angka dengan difficulty default easy.

## Classes Used

1. **Sudoku (program utama)** -
`Sudoku.java` 
   - Program utama yang berisi method main.
   - Arguments (1 arg)
      - **1 arg (difficulty)** - digunakan untuk menentukan kondisi difficulty awal puzzle yang muncul.
   - 3 Class Variable
      - **@buttonSelectionPanel** - Objek JPanel berisi tombol angka.
      - **@sPanel** - Objek panel sudoku.
      - **@savePuzzle** - Objek untuk menyimpan kondisi sudoku yang sudah digenerate.
   - 3 Method
      - **main(args)** - Method main yang akan memunculkan frame (args tidak digunakan).
      - **rebuildInterface(difficulty)** - Method untuk membangun ulang interface.
      - **clearPuzzle()** - Method untuk menghilangkan angka yang sudah diinputkan.

2. **SudokuGenerator** -
`SudokuGenerator.java` 
   - Program untuk generate puzzle sudoku.
   - Arguments (0 arg)
   - 2 Class Variable
      - **@puzzle** - Objek untuk menyimpan kondisi sudoku yang sudah termasking.
      - **@key** - Objek untuk menyimpan kunci dari sudoku.
   - 4 Method
      - **generateRandomSudoku(difficulty)** - Method untuk generate puzzle sudoku dan proses masking.
      - **getPuz()** - Method untuk mendapatkan sudoku yang sudah dimasking.
      - **getKey()** - Method untuk mendapatkan kunci sudoku.
      - **backTrackSudokuSolver(r, c, puzzle)** - Method untuk cek apakah sudoku dapat diselesaikan.

3. **SudokuPanel** -
`SudokuPanel.java` 
   - Program untuk mengatur dan memproses tampilan juga input dari user.
   - Arguments (0 arg)
   - 7 Class Variable
      - **@puzzle** - Objek untuk menyimpan kondisi sudoku yang sudah termasking.
      - **@key** - Objek untuk menyimpan kunci dari sudoku.
      - **@currentlySelectedCol** - Menyimpan posisi kolom yang diklik user.
      - **@currentlySelectedRow** - Menyimpan posisi baris yang diklik user.
      - **@usedWidth** - Menyimpan ukuran lebar dari panel.
      - **@usedHeight** - Menyimpan ukuran panjang dari panel.
      - **@fontSize** - Menyimpan ukuran font.
   - 4 Method
      - **newSudokuPuzzle(puzzle, key)** - Method untuk mendapatkan sudoku beserta key yang bisa digenerate.
      - **OV~paintComponent(g)** - Proses tampilan panel sudoku.
      - **highlight(slotWidth, slotHeight, g2d)** - Method untuk highlight ubin dan font pada ubin sudoku.
      - **messageFromNumActionListener(buttonValue)** - Menentukan aksi dari tombol-tombol pada panel.

4. **SudokuPuzzle** -
`SudokuPuzzle.java` 
   - Program untuk membangun keperluan puzzle sudoku dan untuk mendapatkan sifat-sifat pada puzzle.
   - Arguments (1 arg)
      - **1 arg (puzzle)** - digunakan untuk menentukan kondisi difficulty awal puzzle yang muncul.
   - 7 Class Variable
      - **@board** - Objek untuk menyimpan kondisi sudoku yang sudah termasking.
      - **@mutable** - Objek untuk menyimpan kunci dari sudoku.
      - **@ROWS** - Menyimpan posisi kolom yang diklik user.
      - **@COLUMNS** - Menyimpan posisi baris yang diklik user.
      - **@BOXWIDTH** - Menyimpan ukuran lebar dari panel.
      - **@BOXHEIGHT** - Menyimpan ukuran panjang dari panel.
      - **@VALIDVALUES** - Menyimpan ukuran font.
   - 21 Method
      - **getNumRows()** - Get jumlah baris sudoku yaitu 9.
      - **getNumColumns()** - Get jumlah kolom sudoku yaitu 9.
      - **getBoxWidth()** - Get lebar sub grid sudoku yaitu 3.
      - **getBoxHeight()** - Get tinggi sub grid sudoku yaitu 3.
      - **getValidValues()** - Get angka valid sudoku yaitu 1 sampai 9.
      - **makeMove(row, col, value, isMutable)** - Method untuk mengisikan angka pada sudoku.
      - **isValidMove(row, col, value)** - Cek angka yang diinputkan valid dan ubin bisa diisi.
      - **numInCol(col, value)** - Cek apakah ada angka yang sama dalam satu kolom.
      - **numInRow(row, value)** - Cek apakah ada angka yang sama dalam satu baris.
      - **numInBox(row, col, value)** - Cek apakah ada angka yang sama dalam satu sub grid.
      - **isSlotAvailable(row, col)** - Cek apakah ubin masih kosong.
      - **isSlotMutable(row, col)** - Cek apakah ubin adalah ubin yang sudah dimasking.
      - **getValue(row, col)** - Mendapatkan value dari kolom dan baris tertentu.
      - **getBoard()** - Mendapatkan array dari board sudoku.
      - **isValidValue(value)** - Cek apakah angka ada di antara 1 sampai 9.
      - **inRange(row, col)** - Cek apakah kolom dan baris ada di jangkauan board sudoku.
      - **boardFull()** - Cek apakah board sudoku sudah full diisi angka.
      - **makeSlotEmpty(row, col)** - Method untuk mengosongkan angka pada ubin.
      - **OV~toString()** - Method Override.
      - **initializeBoard()** - Inisialisasi board.
      - **initializeMutableSlots()** - Inisialisasi slot yang bisa diisi.

UML image here

## Notable Assumption and Design App Details

TO;DO
