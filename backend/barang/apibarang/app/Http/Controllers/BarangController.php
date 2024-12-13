<?php

namespace App\Http\Controllers;

use App\Models\Barang;
use Faker\Provider\Base;
use Illuminate\Http\Request;

class BarangController extends Controller
{
    public function index(){
        $barang = Barang::all();
        return view('index',['tampil' => $barang]);
    }

    public function create(){
        return view('create');
    }

    public function store(Request $request){
        Barang::create($request->all());
        return redirect('/barang');
    }

    public function edit($id){
        $barang = Barang::where('id',$id)->get();
        return view('edit',['tampil' => $barang]);
    }

    public function update(Request $request){
        Barang::where('id',$request->id)->update([
            'nama_barang' => $request->nama_barang,
            'merk' => $request->merk,
            'stok' => $request->stok,
        ]);
        return redirect('/barang');
    }

    public function destroy($id){
        Barang::where('id',$id)->delete();
        return redirect('/barang');
    }
}
