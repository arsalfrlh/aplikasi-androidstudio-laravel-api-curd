<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>Daftar Barang</h1>
    <a href="/barang/tambah">Tambah Barang</a>
    <table border="1">
        <tr>
            <th>No</th>
            <th>Nama Barang</th>
            <th>Merk</th>
            <th>Stok</th>
            <th>Aksi</th>
        </tr>
        @php
            $no = 1;
        @endphp
        @foreach ($tampil as $barang)   
        <tr>
            <td>{{ $no++ }}</td>
            <td>{{ $barang->nama_barang }}</td>
            <td>{{ $barang->merk }}</td>
            <td>{{ $barang->stok }}</td>
            <td>
                <a href="/barang/edit/{{ $barang->id }}">Edit</a>
                <form action="/barang/hapus/{{ $barang->id }}" method="POST">
                    @csrf
                    @method('DELETE')
                    <input type="submit" value="Hapus">
                </form>
            </td>
        </tr>
        @endforeach
    </table>
</body>
</html>