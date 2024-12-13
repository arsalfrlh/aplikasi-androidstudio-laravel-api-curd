<?php

namespace App\Http\Controllers;

use App\Models\Barang;
use Illuminate\Http\Request;

class BarangApiController extends Controller
{
    public function index(){
        $barang = Barang::all();
        return response()->json(['message' => 'berhasil menampilkan data barang', 'data' => $barang]);
    }

    public function store(Request $request){
        $barang = Barang::create($request->all());
        return response()->json(['message' => 'berhasil menambahkan data barang', 'data' => $barang]);
    }

    public function edit($id){
        $barang = Barang::where('id',$id)->get();
        return response()->json(['message' => 'berhasil menampilkan data barang yang dipilih', 'data' => $barang]);
    }

    public function update(Request $request,$id){
        $barang = Barang::where('id',$id)->update($request->all());
        return response()->json(['message' => 'berhasil mengupdate data barang', 'data' => $barang]);
    }

    public function destroy($id){
        $barang = Barang::where('id',$id)->delete();
        return response()->json(['message' => 'berhasil menghapus data barang', 'data' => $barang]);
    }
}
