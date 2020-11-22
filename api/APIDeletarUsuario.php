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
		$sql = "DELETE FROM usuario WHERE id = $api_ID";

		$stmt = mysqli_prepare($conn, $sql);

		// Executa a consulta
		mysqli_stmt_execute($stmt);


		//Verifica se algo foi deletado
		if (mysqli_stmt_affected_rows($stmt) > 0) {
			$response["deletado"] = true;
			echo json_encode($response);
		} else {
			$response["deletado"] = false;
			$response["SQL"] = $sql;
			echo json_encode($response);
		}
	} else {

		$response['auth_token'] = false;

        echo json_encode($response);

	}

}


?>