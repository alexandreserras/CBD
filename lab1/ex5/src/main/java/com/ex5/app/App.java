package com.ex5.app;

import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class App 
{
    public static void getAllUsers(JSet set){
        Set<String> a = set.getUser();
        for(String nome: a){
            System.out.print(nome+" ,");
        }
        System.out.println("");
    }
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        boolean control= true;
        int escolha ;
        String user ="";
        JList list = new JList();
        JSet set = new JSet();
        String pre;
        Set<String> a ;
        Set<String> sets ;
        while(control){
            if (user.equals("")){
                    System.out.println("Escolha a opção que pretende");
                    System.out.println("1-Adicionar utilizador ");
                    System.out.println("2-Ligar a um utilizador");
                    System.out.println("3-Ver todos os utilizadores");
                    try {
                        escolha = Integer.parseInt(sc.nextLine());
                        System.out.println(escolha);
                        switch(escolha){
                            case 1:
                                System.out.print("Escolha o seu nome:");
                                Scanner prec = new Scanner(System.in);
                                pre= prec.nextLine();
                                a =set.getUser();
                                if (a.contains(pre)){
                                    System.out.println("User já registado");
                                }
                                else{
                                    user =pre;
                                    set.saveUser(user);
                                    System.out.println("User  registado com sucesso");
                                }
                                break;
                            case 2:
                                System.out.print("Escolha o seu nome:");
                                Scanner case2 = new Scanner(System.in);
                                pre= case2.nextLine();
                                a =set.getUser();
                                if (a.contains(pre)){
                                    System.out.println("Loggin feito");
                                    user =pre;
                                }
                        
                                else{
                                    System.out.println("Utilizador  não encontrado");
                                }
                                break;
                            case 3:
                                getAllUsers(set);
                                break;
                            default:
                                System.out.println("A opção que inseriu não existe");
                        }
                    } catch (Exception e) {
                        System.out.println("A opção escolhida não é numérica !!!");
                    }
                    
            }
            else{
                    System.out.println("Escolha a opção que pretende");
                    System.out.println("1-Ver todos os utilizadores");
                    System.out.println("2-Seguir utilizador");
                    System.out.println("3-Parar de seguir");
                    System.out.println("4-Enviar uma mensagem");
                    System.out.println("5-Ler as  mensagens de quem sigo");
                    System.out.println("6-Ver quem sigo");
                    System.out.println("7-Ver as minhas mensagens");
                    System.out.println("8-Logout");
                    System.out.println("9-Quit");

                    try {
                        escolha = Integer.parseInt(sc.nextLine());
                        switch(escolha){
                            case 1:
                                getAllUsers(set);
                                break;
                            case 2:
                                String follow = sc.nextLine();
                                a =set.getUser();
                                sets =set.getFollows(user);
                                if (a.contains(follow) && ! sets.contains(follow)){
                                    set.followUser(user, follow);
                                    System.out.println("Follow feito");
                                }
                                else{
                                    System.out.println("Impossivel adicionar, talvez já siga este user ou ele não existe");
                                }
                                break;
                            case 3:
                                String unfollow = sc.nextLine();
                                a =set.getUser();
                                sets =set.getFollows(user);
                                if (a.contains(unfollow) && sets.contains(unfollow)){
                                    set.unfollowUser(user, unfollow);
                                    System.out.println("Unfollow dado");
                                }
                                else{
                                    System.out.println("Não segues este utilizador");
                                }
                                break;
                            case 4:
                                String message = sc.nextLine();
                                list.saveUserL(user, message);
                                break;
                            case 5:
                                a = set.getFollows(user);
                                List<String> recebidas;
                                if (a.size() != 0){
                                    for (String pessoa:a){
                                        System.out.println(pessoa+":");
                                        recebidas=list.getUserL(pessoa);
                                        for (String c: recebidas){
                                            System.out.println(c);
                                        }

                                    }
                                }
                                else{
                                    System.out.println("Não segues ninguem");
                                }
                                break;
                            case 6:
                                a=set.getFollows(user);
                                System.out.println("Subscrições : ");
                                for (String x : a){
                                    System.out.print(x+",");
                                }
                                System.out.println("");
                                break;
                            case 7:
                                List<String> mensagens = list.getUserL(user);
                                System.out.println("As minhas mensagens:");
                                for (String msg : mensagens){
                                    System.out.println(msg);
                                }
                                break;
                            case 8:
                                user = "";
                                break;
                            case 9:
                                System.exit(0);
                            default:
                                System.out.println("A opção que inseriu não existe");
                        }
                    } catch (Exception e) {
                        System.out.println("A opção escolhida não é numérica !!!");
                    }

            }

        }
        sc.close();
    }
}
