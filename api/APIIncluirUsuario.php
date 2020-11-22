<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    header('Content-type: application/json');

    $api_token = $_POST["api_token"];
    //$api_ID = $_POST["api_ID"];
    $api_nome = $_POST["api_nome"];
    $api_data_nascimento = $_POST["api_data_nascimento"]; 
    $api_email = $_POST["api_email"];  
    $api_senha = $_POST["api_senha"];      
    
    if ($api_token == 'fabricadedesenvolvedor') {

        require_once('dbConnect.php');

        mysqli_set_charset($conn, $charset);
 
        $response = array();
      
        $sql =  "INSERT INTO usuario (nome, data_nascimento, email, senha) VALUES ('$api_nome', '$api_data_nascimento', '$api_email', '$api_senha')";
       
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        
        // Verifica se algo foi adicionado
        if (mysqli_stmt_affected_rows($stmt) > 0) {

           $response["adicionado"] = true;
            
            echo json_encode($response); //Objeto Json
            
        } else {

            $response["adicionado"] = false;
            $response["SQL"] = $sql;
            
            echo json_encode($response);
        }
    } else {

        $response['auth_token'] = false;

        echo json_encode($response);
    }
}
?>
