<?php
include("config.php");

$nama_penduduk= $_POST['nama_penduduk'];
$ttl_penduduk= $_POST['ttl_penduduk'];
$hp_penduduk= $_POST['hp_penduduk'];
$alamat_penduduk = $_POST['alamat_penduduk']

$sql = "INSERT INTO penduduk (nama_penduduk, ttl_penduduk, hp_penduduk, alamat_penduduk) values ('$nama_penduduk', '$ttl_penduduk',  '$hp_penduduk', '$alamat_penduduk' )";
$query = mysqli_query($db, $sql);

//apakah query update berhasil
if ($query) {

}else{
//kalu gagal tampilkan pesan
die("gagal menyimpan perubahan....");
}

?>
