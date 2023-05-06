import { useRouteError } from "react-router-dom";
import CadastroField from "../CadastroField";

export default function CadastroErrorHandler(){
    const error = useRouteError();

    if(error.status === 400){
        return <CadastroField statusError={true}/>
    }
}