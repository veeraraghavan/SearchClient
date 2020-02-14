package org.project.searchclient;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App client =new App();
        Scanner scanner = new Scanner(System.in);
        Request request = new Request();
        RestClient restClient = new RestClient();
        request.setQuery(new Query());
        int state = 0;
        client.print(client.getNextLine(state++));

        while(scanner.hasNextLine()){
            if (state==1){
                request.setEntity(client.getDescriptionforEntity(Integer.parseInt(scanner.nextLine())));
                client.print(client.getNextLine(state++));
            }else if(state==2){
                request.getQuery().setField(client.getAttributeforField(Integer.parseInt(scanner.nextLine())));
                request.getQuery().setOperator("=");
                client.print(client.getNextLine(state++));
            }else if(state==3){
                request.getQuery().setValue(scanner.nextLine());
                System.out.println(restClient.post(request));
                state=0;
                request.clear();
            }
        }
        System.out.println( "Hello World!" );
    }
    public List<String> getNextLine(int state){
        ArrayList<String> result = new ArrayList<>();
        if (state==0){
            result.add("Enter the entity");
            result.add("1.Organization      2.Ticket    3.User");
        }else if(state==1){
            result.add("Enter field to search on 1.Name     2.ID     ");
        }else if(state==2){
            result.add("Enter field value");
        }
        return result;

    }
    public void print(List<String> lines){
        for(String line:lines){
            System.out.println(line);
        }
    }
    public String getDescriptionforEntity(int entity){
        switch (entity){
            case 1:
                return "organization";
            case 2:
                return "ticket";
            default:
                return "user";
        }

    }
    public String getAttributeforField(int entity){
        switch (entity){
            case 1:
                return "name";
            case 2:
                return "id";
            default:
                return "user";
        }

    }
}
@Data
class Request{
    private String entity;
    private Query query;
    public void clear(){
        this.entity=null;
        this.query.clear();
    }

}
@Data
class Query{
    private String operator;
    private String value;
    private String field;
    public void clear(){
        this.operator=null;
        this.field=null;
        this.value=null;
    }
}
