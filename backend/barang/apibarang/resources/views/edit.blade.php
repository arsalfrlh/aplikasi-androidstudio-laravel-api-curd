<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>Tambah data Barang</h1>
    <form action="/barang/edit/proses" method="POST">
        @csrf
        @method('PUT')
        @foreach ($tampil as $barang)
        <table>
            <tr>
                <td>Nama Barang</td>
                <td><input type="text" name="nama_barang" value="{{ $barang->nama_barang }}"><input type="hidden" name="id" value="{{ $barang->id }}"></td>
            </tr>
            <tr>
                <td>Merk</td>
                <td><input type="text" name="merk" value="{{ $barang->merk }}"></td>
            </tr>
            <tr>
                <td>Stok</td>
                <td><input type="number" name="stok" value="{{ $barang->stok }}"></td>
            </tr>
            <tr>
                <td><a href="/barang">Kembali</td>
                <td><input type="submit" value="Simpan"></td>
            </tr>
        </table>
        @endforeach
    </form>
</body>
</html>