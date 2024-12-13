<?php

use App\Http\Controllers\BarangController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});
Route::get('/barang',[BarangController::class,'index']);
Route::get('/barang/tambah',[BarangController::class,'create']);
Route::post('/barang/tambah/proses',[BarangController::class,'store']);
Route::get('/barang/edit/{id}',[BarangController::class,'edit']);
Route::put('/barang/edit/proses',[BarangController::class,'update']);
Route::delete('/barang/hapus/{id}',[BarangController::class,'destroy']);
