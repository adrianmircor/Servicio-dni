
package com.mycompany.pruebamaven;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ClaseJson implements Serializable {

    @SerializedName("result")
    @Expose
    private Result resultadito;

    public Result getResultadito() {
        return resultadito;
    }

    public void setResultadito(Result resultadito) {
        this.resultadito = resultadito;
    }

}
