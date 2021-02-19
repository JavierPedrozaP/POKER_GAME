package com.example.poker;


import java.util.*;

public class poker {

    public static class Carta{ //Se crea una clase
        // estatica para poder ser accedida sin la
        // necesidad de tener una instancia
        String palo; //Se declara las variables
        String valor; //Se declara las variables
        String color; //Se declara las variables

        Carta(String palo, String color, String valor) {
            this.palo = palo;
            this.valor = valor;
            this.color = color;
        }

        //Se declaran publico los valores que seran utilizados en la clase DECK

        // Se crean los metodos y constructores
        public Carta(String palo, String color) {
            this.palo = palo;
            this.color = color;
        }

        public String getPalo() {
            return palo;
        }

        public void setPalo(String palo) {
            this.palo = palo;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }

        public void setValor(Integer valor) {
            if (valor <= 10) {//Se crea la Estructura booleana if-else y se les da los valores a los palos
                if (valor == 1) this.valor = "A";
                this.valor = valor.toString();
            } else {
                if (valor == 11) this.valor = "J";
                else if (valor == 12) this.valor = "Q";
                else this.valor = "K";
            }
        }


        @Override
        public String toString() {//Se Realiza la estrcutura de la impresion y como debe estar organizada
            String mensaje =  palo +  ", " + color + ", " + valor ;
            return String.format(mensaje, palo, color, valor);
        }

    }

    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);//sirve para hacer que el usuario
        // interactue con el codigo
        int option = 0;
        boolean salir = false;// Se agraga la variable salir para que el codigo tenga
        // la  opcion de poder parar el programa cuando el usuario lo desee

        Deck deck = new Deck();
        deck.crearBaraja();

        while (!salir) {//Se crea un bucle para que mostrar un menu tantas veces sea
            // hasta que el usuario digitalice la opcion "salir para parar el codigo"

            System.out.println("WELCOME TO THE POKER");
            System.out.println("Elige un Modo:");
            System.out.println("1.Mezclar Deck");
            System.out.println("2.Sacar una carta");
            System.out.println("3.Carta al azar");
            System.out.println("4.Generar una mano de 5 cartas");
            System.out.println("5.Salir");


            try {
                System.out.println("Introduce un Modo");
                option = scanner.nextInt();

                switch (option) {

                    case 1:
                        deck.shuffle();
                        System.out.println("Se mezcló el Deck.");
                        System.out.println("\n");
                        System.out.println("Modo Finalizado");
                        System.out.println("\n");
                        System.out.println("Escoja Otro Modo");
                        System.out.println("\n");
                        break;

                    case 2:
                        deck.head();
                        System.out.println("Esta es la Primera Carta Del Deck");
                        System.out.println("\n");
                        System.out.println("Modo Finalizado");
                        System.out.println("\n");
                        System.out.println("Escoja Otro Modo");
                        System.out.println("\n");
                        break;

                    case 3:
                        deck.pick();
                        System.out.println("Este es el Modo azar");
                        System.out.println("\n");
                        System.out.println("Modo Finalizado");
                        System.out.println("\n");
                        System.out.println("Escoja Otro Modo");
                        System.out.println("\n");
                        break;

                    case 4:
                        deck.hand();
                        System.out.println("Estas son tus cinco cartas del Deck");
                        System.out.println("\n");
                        System.out.println("Modo Finalizado");
                        System.out.println("\n");
                        System.out.println("Escoja Otro Modo");
                        System.out.println("\n");
                        break;

                    case 5:
                        System.out.println("Gracias por Jugar"+"\n"+"GAME OVER");
                        return;

                    default:
                        throw new IllegalStateException("Modo invalida " + option);
                        //Se ejecuta cuando el usuario se equivoca de opcion o se realizo una ejecucion incorrecta
                }
            }catch (Exception e) {// Se agrega un Try-Catch
                // Con la intencion de que si el usuario se equivoca a digitalizar la
                // opcion se le indique el error y tenga la oportunidad de volver a
                // escoger la opcion correcta
                    System.out.println(" NO INTRODUJO EL MODO CORRECTO CORRECTA"+ "\n");
                    System.out.println("\n\n\n"+"Intentelo de Nuevo");
            }
        }
    }

    public static class Deck{ //Se crea una clase estatica
        // para poder ser accedida sin la necesidad de tener una instancia


        private HashMap<String,String> palos = new HashMap <String, String>();
        //Se implementa una coleccion de elementos donde a cada elemento le corresponde una llave que lo identifica
        private ArrayList <Carta> baraja = new ArrayList<Carta>();
        //Se crea una lisa de una colección ordenada de elementos que se almacenan de forma dinámica
        private String strFormat = "Quedan %s Cartas en el Deck";//Muestra cuantas cartas quedan en el Deck
        int a;



        public ArrayList<Carta> getBaraja(){
            return baraja;
        }



        public void mazo(){//Se crea mazo que tendra el palo y el color
            palos.put("Diamante", "Rojo");
            palos.put("Trebol","Negro");
            palos.put("Pica","Negro");
            palos.put("Corazon","Rojo");
        }



        public void crearBaraja(){//se realiza la creacion del deck
            mazo();

            for(Map.Entry<String,String> palo:palos.entrySet()){
                var paloCard = palo.getKey();
                var color = palo.getValue();

                for(int i=1; i <= 13; i++){
                    Carta card = new Carta(paloCard, color);
                    card.setValor(i);
                    baraja.add(card);
                }
            }


        }



        public void shuffle() {//mezclar el deck
            Collections.shuffle(baraja);
        }



        public void head () throws Exception{//Mostrar la primera carta del deck y la carta deberá removerse del deck.
            var card = baraja.get(baraja.size()-1);
            baraja.remove(card);
            System.out.println(card.toString());
            System.out.println(String.format(strFormat,baraja.size()));
            a= baraja.size();

            if (a == 0) {
                throw new Exception("¡YA NO HYA CARTAS EN EL DECK!");//Manda un mensaje cuando el deck queda vacio
            }
        }



        private Carta randomCard(){//Seleccion de una carta al azar y la carta deberá removerse del deck
            var max = baraja.size()-1;
            var rnd = (int)Math.floor(Math.random()*(0-max + 1)+max);
            return baraja.get(rnd);
        }


        public void pick () throws Exception{//Seleccion de una carta al azar y la carta deberá removerse del deck
            var card = randomCard();
            baraja.remove(card);
            System.out.println(card.toString());
            System.out.println(String.format(strFormat,baraja.size()));
            a = baraja.size();
            if (a == 0) {
                throw new Exception("¡YA NO HYA CARTAS EN EL DECK!");//Manda un mensaje cuando el deck queda vacio
            }
        }

        private void printHand(ArrayList<Carta> cards){
            for (var card: cards) System.out.println(card.toString());
        }



        public void hand() throws Exception{//Regresa un arreglo de cinco cartas del deck y las cartas se removen del deck.
            if(baraja.size() <= 5){
                for (var card:baraja) {
                    printHand(baraja);
                    a = baraja.size();
                    if (a <= 5) {
                        throw new Exception("¡YA NO HYA CARTAS EN EL DECK!");//Manda un mensaje cuando el deck queda vacio
                    }
                }

            }else{
                var cards = new ArrayList<Carta>();
                Carta carta;
                for (int i=1; i<=5;i++){
                    carta = randomCard();
                    baraja.remove(carta);
                    cards.add(carta);

                }
                printHand(cards);
                System.out.println(String.format(strFormat,baraja.size()));

            }

        }





    }


}
