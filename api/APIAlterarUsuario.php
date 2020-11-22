<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    header('Content-type: application/json');

    $api_token = $_POST["api_token"];
    $api_ID = $_POST["api_ID"];
    $api_nome = $_POST["api_nome"];
    $api_data_nascimento = $_POST["api_data_nascimento"];  
    $api_email = $_POST["api_email"];  
    $api_senha = $_POST["api_senha"];  
    
    if ($api_token == 'fabricadedesenvolvedor') {

        require_once('dbConnect.php');

        mysqli_set_charset($conn, $charset);
 
        $response = array();
      
        $sql =  "UPDATE usuario SET nome = '$api_nome', data_nascimento = '$api_data_nascimento', email = '$api_email', senha = '$api_senha' WHERE id = $api_ID";
       
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        
        // Verifica se algo foi alterado
        if (mysqli_stmt_affected_rows($stmt) > 0) {

           $response["alterado"] = true;
            
            echo json_encode($response); //Objeto Json
            
        } else {

            $response["alterado"] = false;
            $response["SQL"] = $sql;
            
            echo json_encode($response);
        }
    } else {

        $response['auth_token'] = false;

        echo json_encode($response);
    }
}
?>
