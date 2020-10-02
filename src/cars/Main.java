
package cars;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import pojo.Car;


public class Main {

    private static SessionFactory factory;
    
    public static void main(String[] args) {
        
        try{
           factory = new AnnotationConfiguration().configure().addAnnotatedClass(Car.class).buildSessionFactory();
        }catch(Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
        
        Main M = new Main();
        int meni;
        int id;
        String mark;
        String model;
        int cc;
        int hp;
        Scanner input = new Scanner(System.in);
        
        System.out.println("=============================================");
        System.out.println("JAVA CLI APPLICATION ABOUT CARS AND HIBERNATE");
        System.out.println("---------------------------------------------");
        System.out.println("CREATE BY: MLADEN SIMEONOVIC - 2020");
        System.out.println("=============================================");
        System.out.println("MENI");
        System.out.println("[1] ADD NEW CAR");
        System.out.println("[2] UPDATE EXIST CAR(HP)");
        System.out.println("[3] DELETE EXIST CAR");
        System.out.println("[4] LIST ALL CARS");
        System.out.println("---------------------------------------------");
        System.out.println("Please choose ONE of options: ");
        meni = input.nextInt();
        
        switch(meni) {
            case 1:
                System.out.println("Please input a MARK of car: ");
                mark = input.next();
                System.out.println("Please input a MODEL of car: ");
                model = input.next();
                System.out.println("Please input a CC of engine: ");
                cc = input.nextInt();
                System.out.println("Please input a HP of car: ");
                hp = input.nextInt();
                
                M.addCar(mark, model, cc, hp);
                break;
                
            case 2:
                M.listCar();
                System.out.println("Please choose ONE of cars: ");
                id = input.nextInt();
                System.out.println("Please input a new value of HP: ");
                hp = input.nextInt();
                
                M.updateCar(id, hp);
                break;
                
            case 3:
                M.listCar();
                System.out.println("Please choose ONE of cars: ");
                id = input.nextInt();
                
                M.deleteCar(id);
                break;
                
            case 4:
                M.listCar();
                break;
                
            default:
                System.out.println("INVALID OPTION!");
                break;
        }
    }
    
    public Integer addCar(String mark, String model, int cc, int hp) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer carID = null;
        
        try{
            tx = session.beginTransaction();
            Car car = new Car(mark, model, cc, hp);
            carID = (Integer) session.save(car);
            tx.commit();
        }catch(HibernateException e) {
            if(tx!=null){
                tx.rollback();
                e.printStackTrace();
            }
        }finally {
            session.close();
        }
        
        return carID;
    }
    
    public void listCar() {
        Session session = factory.openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            List cars = session.createQuery("FROM Car").list();
            for(Iterator i = cars.iterator(); i.hasNext();) {
                Car car = (Car) i.next();
                System.out.print(" ID: " + car.getIdCar());
                System.out.print(" Mark: " + car.getMark());
                System.out.print(" ,Model: " + car.getModel());
                System.out.print(" ,CC: " + car.getCc());
                System.out.println(" ,HP: " + car.getHp());
            }
            tx.commit();
        }catch(HibernateException e) {
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    
    public void updateCar(Integer carID, int hp) {
        Session session = factory.openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            Car car = (Car) session.get(Car.class, carID);
            car.setHp(hp);
            session.update(car);
            tx.commit();
        }catch(HibernateException e) {
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    
    public void deleteCar(Integer carID) {
        Session session = factory.openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            Car car = (Car) session.get(Car.class, carID);
            session.delete(car);
            tx.commit();
        }catch(HibernateException e) {
            if(tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
    
}
