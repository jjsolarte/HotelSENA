package co.jjsolarte.hotelsena.interfaz;

import java.util.List;

import co.jjsolarte.hotelsena.model.Mensaje;
import co.jjsolarte.hotelsena.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {

    String GET_PATH = "posts";
    String GET_MENSAJES = "rolUsuario";

    @GET(GET_PATH)
    Call<List<Post>> listarPost();

    @GET(GET_MENSAJES)
    Call<Mensaje> listarMensajes();

}
