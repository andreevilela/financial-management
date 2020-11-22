<?php

// GET - POST - DELETE - PUT - OUTROS
	
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

	header('Content-type: application/json');

	// TOKEN
	$api_token = $_POST["api_token"];
    $api_ID = $_POST["api_ID"];

	//$api_token = $_POST["api_token"];

	if ($api_token == "fabricadedesenvolvedor") {

		// Conexão com o Banco de Dados
		require_once('dbConnect.php');

		// Definir UTF8 para conexão
		mysqli_set_charset($conn, $charset);

		$response = array();

		// Prepara a consulta SQL
		$stmt = mysqli_prepare($conn, "SELECT id, nome, data_nascimento, email, senha FROM usuario WHERE id = $api_ID");

		// Executa a consulta
		mysqli_stmt_execute($stmt);

		// Salva o resultado da consulta
		mysqli_stmt_store_result($stmt);

		// Gera os dados a serem apresentados em variaveis conforme o select
		mysqli_stmt_bind_result($stmt, $id, $nome, $data_nascimento, $email, $senha);

		// Apresenta dados da consulta
		// var_dump($stmt);


		if (mysqli_stmt_num_rows($stmt) > 0){

			while (mysqli_stmt_fetch($stmt)) {
				array_push($response, array("id" => $id, "nome" => $nome, "data_nascimento" => $data_nascimento, "email" => $email, "senha" => $senha));
			}

			echo json_encode($response);

		} else {

			echo json_encode($response);

		}
		
	} else {

		$response['auth_token'] = false;

        echo json_encode($response);

	}

}


?>