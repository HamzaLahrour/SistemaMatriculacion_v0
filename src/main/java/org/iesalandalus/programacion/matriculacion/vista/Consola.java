package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.iesalandalus.programacion.matriculacion.dominio.Alumno.FORMATO_FECHA;

public class Consola {

    private Consola (){

    }

    public static void mostrarMenu (){

       Opcion [] opciones = Opcion.values();

        for (Opcion opcion : opciones){
            System.out.println(opcion);
        }

    }

    public static Opcion elegirOpcion(){

        int opcion=0;


       Opcion [] opciones = Opcion.values();

        System.out.println("Elige una opcion: ");
        opcion= Entrada.entero();

        if (opcion<0 || opcion>19){
            throw new IllegalArgumentException("ERROR: La opcion introducida no se encuentra en el rango.");
        }

        return opciones[opcion];


    }

    public static Alumno leerAlumno(){
        String nombre;
        String dni;
        String correo;
        String telefono;
        LocalDate fechaNacimiento;


        System.out.println("Introduzca el nombre del alumno: ");
        nombre=Entrada.cadena();
        System.out.println("Introduzca el dni del alumno: ");
        dni=Entrada.cadena();
        System.out.println("Introduzca el correo electronico del alumno: ");
        correo=Entrada.cadena();
        System.out.println("Introduzca el telefono del alumno: ");
        telefono=Entrada.cadena();
        fechaNacimiento=leerFecha("Introduzca la fecha de nacimiento del alumno en el formato dd/MM/YYYY: ");



        return new Alumno(nombre,dni,correo,telefono,fechaNacimiento);
    }

    public static Alumno getAlumnoPorDni (){

        String leerDni;

        System.out.println("Introduzca el dni del alumno: ");
        leerDni=Entrada.cadena();


        return new Alumno("nombre",leerDni,"hamzahamza2@gmail.com","674123987",LocalDate.of(2004,10,21));
    }

    public static LocalDate leerFecha (String mensaje){

        String fechaEntrada;


        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(FORMATO_FECHA);

        System.out.println(mensaje);
        fechaEntrada=Entrada.cadena();

        LocalDate fechaEstandar;

        try {
            fechaEstandar=LocalDate.parse(fechaEntrada,formatoFecha);

        }catch (Exception e){
            throw new IllegalArgumentException("ERROR: La fecha introducida no tiene un formato valido.");

        }

        return fechaEstandar;

    }

    public static Grado leerGrado (){

        int opcion=0;

        for (Grado grado : Grado.values()){

            System.out.println(grado.imprimir());

        }

        do {
            System.out.println("Elige el grado que vas a elegir, introduzca un numero valido dentro del rango: ");
            opcion=Entrada.entero();
        }while (opcion>2 || opcion<0);



        switch (opcion){

            case 0:
                return Grado.GDCFGB;
            case 1:
                return Grado.GDCFGM;
            default:
                return Grado.GDCFGS;
        }



    }

    public static CicloFormativo leerCicloFormativo (){

        int codigo=0;
        String familiaProfesional;
        String nombre;
        int horas;

        Grado grado;

        System.out.println("Introduce el codigo del ciclo formativo: ");
        codigo=Entrada.entero();
        System.out.println("Introduzca la familia profesional del ciclo formativo: ");
        familiaProfesional=Entrada.cadena();
        grado=leerGrado();
        System.out.println("Introduzca el nombre del ciclo formativo: ");
        nombre=Entrada.cadena();
        System.out.println("Introduzca las horas del ciclo formativo: ");
        horas=Entrada.entero();

        return new CicloFormativo(codigo,familiaProfesional,grado,nombre,horas);

    }

    public static void mostrarCiclosFormativos (CiclosFormativos ciclosFormativos){

        for (CicloFormativo cicloFormativo : ciclosFormativos.get()){
            System.out.println(cicloFormativo);
        }

    }

    public static CicloFormativo getCicloFormativoPorCodigo (){

        int codigo=0;


        System.out.println("Introduzca el codigo del ciclo formativo: ");
        codigo=Entrada.entero();

        return new CicloFormativo(codigo,"familia profesional",Grado.GDCFGS,"ciclofor",250);

    }

    public static Curso leerCurso (){

        int opcion=0;

        for (Curso curso : Curso.values()){
            System.out.println(curso.imprimir());
        }

        do {
            System.out.println("Elige una opcion dentro del rango: ");
            opcion=Entrada.entero();
        }while (opcion<1 || opcion>2);

        if (opcion==1){
            return Curso.PRIMERO;
        }else {
            return Curso.SEGUNDO;
        }

    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado (){

        int opcion=0;

        for (EspecialidadProfesorado especialidadProfesorado : EspecialidadProfesorado.values()){
            System.out.println(especialidadProfesorado.imprimir());
        }

        do {
            System.out.println("Elige una opcion dentro del rango: ");
            opcion=Entrada.entero();
        }while (opcion<1 || opcion>3);

        switch (opcion){
            case 1:
                return EspecialidadProfesorado.INFORMATICA;
            case 2:
                return EspecialidadProfesorado.SISTEMAS;
            default:
                return EspecialidadProfesorado.FOL;
        }

    }

    public static Asignatura leerAsignatura (CiclosFormativos ciclosFormativos){

        String codigo;
        String nombre;
        int horasAnuales=0;
        int horasDesdoble=0;
        Curso curso;
        EspecialidadProfesorado especialidadProfesorado;
        CicloFormativo cicloFormativo;

        System.out.println("Introduzca el codigo de la asignatura: ");
        codigo=Entrada.cadena();
        System.out.println("Introduzca el nombre de la asignatura: ");
        nombre=Entrada.cadena();
        System.out.println("Introduzca las horas anuales de la asignatura: ");
        horasAnuales=Entrada.entero();
        curso=leerCurso();
        System.out.println("Introduzca el numero de horas desdobles de la asignatura: ");
        horasDesdoble=Entrada.entero();
        especialidadProfesorado=leerEspecialidadProfesorado();
        cicloFormativo=getCicloFormativoPorCodigo();

        return new Asignatura(codigo,nombre,horasAnuales,curso,horasDesdoble,especialidadProfesorado,cicloFormativo);

    }

    public static Asignatura getAsignaturaPorCodigo (){

        String codigo;

        System.out.println("Introduzca el codigo de la asignatura: ");
        codigo=Entrada.cadena();

        CicloFormativo cicloFormativo= new CicloFormativo(8886,"familiaprofe",Grado.GDCFGB,"nombre",8);


        return new Asignatura(codigo,"nombre",111,Curso.PRIMERO,3,EspecialidadProfesorado.FOL,cicloFormativo);
    }

    public static void mostrarAsignaturas (Asignaturas asignaturas){

        for (Asignatura asignatura: asignaturas.get()){
            System.out.println(asignatura);
        }

    }

    public static boolean asignaturaYaMatriculada (Asignatura [] asignaturasMatricula, Asignatura asignatura){

        for (int i=0;i<asignaturasMatricula.length;i++){

            if (asignaturasMatricula[i].equals(asignatura)){

                System.out.println("ERROR: La asignatura introducida ya está matriculada.");
                return true;
            }

        }

        return false;
    }



    public static Matricula leerMatricula (Alumnos alumnos, Asignaturas asignaturas)throws OperationNotSupportedException{

        int idMatricula=0;
        String cursoAcademico;
        LocalDate fechaMatriculacion;
        Alumno alumno;
        int numeroAsignaturas=0;
        CiclosFormativos ciclosFormativos = new CiclosFormativos(3);

        Asignatura [] coleccionAsignaturas;



        System.out.println("Introduzca el codigo de la matricula: ");
        idMatricula=Entrada.entero();

        System.out.println("Introduzca el curso academico en formato dd-dd: ");
        cursoAcademico=Entrada.cadena();
        fechaMatriculacion=leerFecha("Introduzca la fecha de matriculacion en el formato dd/MM/YYYY: ");
        alumno=getAlumnoPorDni();
        alumno=alumnos.buscar(alumno);

        System.out.println("Introduzca el numero de asignaturas de asignaturas de la matricula: ");
        numeroAsignaturas=Entrada.entero();

        if (numeroAsignaturas<=0){
            throw new IllegalArgumentException("ERROR: No te puedes matricular con 0 asignaturas.");
        } else if (numeroAsignaturas>10) {
            throw new IllegalArgumentException("ERROR: El numero maximo de asignaturas por matricula son 10.");
        }

        coleccionAsignaturas=new Asignatura[numeroAsignaturas];

        CicloFormativo cicloFormativo;
        Asignatura asignatura;
        Asignatura asignatura1;

        for (int i=0; i<numeroAsignaturas;i++){

            System.out.println("Introduzca la asignatura: " + (i+1));

            asignatura=getAsignaturaPorCodigo();
            asignatura1=asignaturas.buscar(asignatura);

            if (i==0){
                coleccionAsignaturas[i]=asignatura1;

            }

            if (i>0){
                if (!asignaturaYaMatriculada(coleccionAsignaturas,asignatura1))
                coleccionAsignaturas[i]=asignatura1;
            }

        }



        return new Matricula(idMatricula,cursoAcademico,fechaMatriculacion,alumno,coleccionAsignaturas);

    }

    public static Matricula getMatriculaPorIdentificador()throws OperationNotSupportedException{

        int idMatricula=0;
        LocalDate fechaMatriculacion;
        fechaMatriculacion=LocalDate.now();
        Alumno alumno = new Alumno("nombre","31305842J","hamzahamza2@gmail.com","674123987",LocalDate.of(2004,10,21));

        Asignatura [] coleccionAsignaturas = new Asignatura[6];

        System.out.println("Introduzca el identificador de la matricula para eliminar/buscar: ");
        idMatricula=Entrada.entero();


        return new Matricula(idMatricula,"24-25",fechaMatriculacion,alumno,coleccionAsignaturas);
    }






}
