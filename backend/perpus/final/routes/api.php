<?php

use App\Http\Controllers\BukuApiController;
use App\Http\Controllers\SessionApiController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

//route untuk mengambil data user dari token
Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});
Route::post('/perpus/register',[SessionApiController::class,'register']);
Route::post('/perpus/login',[SessionApiController::class,'login']);

Route::get('/perpus/buku',[BukuApiController::class,'index']);
Route::post('/perpus/buku/tambah',[BukuApiController::class,'create_buku']);
Route::get('/perpus/buku/edit/{id}',[BukuApiController::class,'edit_buku']);
Route::put('/perpus/buku/update/{id}',[BukuApiController::class,'update_buku']);
Route::delete('/perpus/buku/hapus/{id}',[BukuApicontroller::class,'hapus_buku']);
