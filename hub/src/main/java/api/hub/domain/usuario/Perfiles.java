/*
package api.hub.domain.usuario;

import org.jetbrains.annotations.NotNull;

public enum Perfiles {

    ADMINISTRADOR("Administrador"),
    INSTRUCTOR("Instructor"),
    ESTUDIANTE("Estudiante");

    private static String text;
    private String perfil;

    Perfiles(String perfil){
        this.perfil = perfil;
    }

    public static @NotNull Perfiles fromString(String text){
        Perfiles.text = text;
        for(Perfiles perfil: Perfiles.values()){
            if (perfil.perfil.equalsIgnoreCase(text)){
                return perfil;
            }
        }
        throw new IllegalArgumentException("Ningún perfil fue encontrado: " + text);
    }
}*/
