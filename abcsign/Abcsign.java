package abcsign;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;

import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_MODENA;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Abcsign extends Application {
   MediaPlayer mp;
    Scene log_in;
    Scene sign_up;
    Scene section;
    Scene Playpage;
    Scene topepage;
    Scene Lessonspage;
    Scene challange;
    String user_name;
    static int c =0;
    
    //the variable of challange page
      int chletter ;  
      int chlevel ; //to specify the level
      Label chwrong = new Label(null); //the hidden message 
      int timer1 ; 
      Timeline tm;
        
      //method of the wrong message
     public void wrongchoice(){
      chwrong.setText("You choose the wrong letter please try Again");
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished( dd -> chwrong.setText(null));
                pause.play();
     
     }
     
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
               //////style///
         DropShadow dropShadow = new DropShadow();
             dropShadow.setRadius(5.0);
         dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.web("#a19a9a"));

        ////////// log in scene //////////
        Label msg = new Label();
        Label logIn = new Label("log in");
        logIn.setAlignment(Pos.TOP_CENTER);
        logIn.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #707070;-fx-font-size:30");
        Label user = new Label("username: ");
        user.setStyle("-fx-font-family: STSong ;-fx-text-fill: #707070;-fx-font-size:20");
        TextField username = new TextField();
        username.setStyle("-fx-background-radius:20;");
         username.setMaxSize(40, 300);
        username.setMinSize(250, 40);
        username.setEffect(dropShadow);
        
        Label pass = new Label("password: ");
        pass.setStyle("-fx-font-family: STSong ;-fx-text-fill: #707070;-fx-font-size:20");
        PasswordField password = new PasswordField();
        password.setStyle("-fx-background-radius:20;-fx-background-size:5");
        password.setEffect(dropShadow);
        password.setMaxSize(40, 300);
        password.setMinSize(250, 40);
      
        Rectangle squareLogIn = new Rectangle(500, 100, 450, 450);
        squareLogIn.setFill(Color.web("#fbf6f5"));
        squareLogIn.setArcHeight(200);
        squareLogIn.setArcWidth(200);
        
        HBox radio = new HBox(15);
        radio.setAlignment(Pos.CENTER);
        RadioButton girl = new RadioButton("girl");
        RadioButton boy = new RadioButton("boy");
        ToggleGroup btngroup = new ToggleGroup();
        girl.setToggleGroup(btngroup);
        boy.setToggleGroup(btngroup);
        radio.getChildren().addAll(girl, boy);
        
        Button btn = new Button("log in");
        btn.setStyle("-fx-padding: 10 30 10 30;-fx-background-radius: 50;-fx-font-family: STSong;-fx-text-fill: WHITE;-fx-background-color: #bf9d9e;-fx-font-size:20");
        Label signUp = new Label("You don't have an account? sign up");
        signUp.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #707070;-fx-font-size:15");
       // Label se = new Label("sec");

        VBox group = new VBox(20);
        group.setPadding(new Insets(20, 20, 20, 20));
        group.setAlignment(Pos.CENTER);
        group.getChildren().addAll(logIn, user, username, pass, password, btn, signUp, msg);
        
        StackPane paneLogIn = new StackPane();
        paneLogIn.setPadding(new Insets(100, 100, 100, 100));
        paneLogIn.getChildren().addAll(squareLogIn, group);

        Rectangle squareVideo = new Rectangle(500, 100, 450, 450);
        squareVideo.setFill(Color.web("#fbf6f5"));
        squareVideo.setArcHeight(200);
        squareVideo.setArcWidth(200);
         Rectangle squareVideo2 = new Rectangle(500, 100, 450, 450);
        squareVideo2.setFill(Color.web("#fbf6f5"));
        squareVideo2.setArcHeight(200);
        squareVideo2.setArcWidth(200);
        
        MediaPlayer mediaPlayer;
        
        String path= "C:\\Users\\lulu-\\Desktop\\project-AP\\abcsign\\src\\v1.mp4";   
        Media media = new Media(new File(path).toURI().toString()); 
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView (mediaPlayer);
        mediaView.setFitHeight(390);
        mediaView.setFitWidth(390);
        mediaView.setEffect(dropShadow);
        mediaView.setStyle("-fx-border-color: black");
        
        
        StackPane vp = new StackPane();
        vp.setPadding(new Insets(100, 100, 100, 100));
        vp.getChildren().addAll(squareVideo2, mediaView);

        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-image: url('abc.jpeg')");
        root.setLeft(paneLogIn);
        root.setRight(vp);
        
        log_in = new Scene(root, 1280, 719);
        
        ////////// sign up scene //////////
        Label msg2 = new Label();
        Label signUpLabel = new Label("sign up");
        signUpLabel.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #707070;-fx-font-size:30");
       
        Label userSignUp = new Label("username: ");
        userSignUp.setStyle("-fx-font-family: STSong ;-fx-text-fill: #707070;-fx-font-size:20");
        TextField usernameSignUp = new TextField();
        usernameSignUp.setStyle("-fx-background-radius:20;");
        usernameSignUp.setMaxSize(40, 300);
        usernameSignUp.setMinSize(250, 40);
        usernameSignUp.setEffect(dropShadow);
       
        Label passSignUp = new Label("password: ");
        passSignUp.setStyle("-fx-font-family: STSong ;-fx-text-fill: #707070;-fx-font-size:20");
        PasswordField passwordSignUp = new PasswordField();
        passwordSignUp.setStyle("-fx-background-radius:20;");
        passwordSignUp.setMaxSize(40, 300);
        passwordSignUp.setMinSize(250, 40);
        passwordSignUp.setEffect(dropShadow);
       
        Label email = new Label("email: ");
        email.setStyle("-fx-font-family: STSong ;-fx-text-fill: #707070;-fx-font-size:20");
        TextField emailSignUp = new TextField();
         emailSignUp.setStyle("-fx-background-radius:20;");
        emailSignUp.setMaxSize(40, 300);
        emailSignUp.setMinSize(250, 40);
        emailSignUp.setEffect(dropShadow);


        Rectangle squareSignUp = new Rectangle(500, 100, 450, 520);
        squareSignUp.setFill(Color.web("#fbf6f5"));
        squareSignUp.setArcHeight(200);
        squareSignUp.setArcWidth(200);
        Button btnSignUp = new Button("sign up");
        btnSignUp.setStyle("-fx-padding: 10 30 10 30;-fx-background-radius: 50;-fx-font-family: STSong;-fx-text-fill: WHITE;-fx-background-color: #bf9d9e;-fx-font-size:20");
        btnSignUp.setAlignment(Pos.CENTER);
        Label back = new Label("back ");
        back.setStyle("-fx-font-family: STSong ;-fx-text-fill: #707070;-fx-font-size:20");

        VBox groupSignUp = new VBox(20);
        groupSignUp.setPadding(new Insets(20, 20, 20, 20));
        groupSignUp.setAlignment(Pos.CENTER);
        groupSignUp.getChildren().addAll(signUpLabel, userSignUp, usernameSignUp, passSignUp, passwordSignUp, email, emailSignUp, radio, btnSignUp, msg2);
        
        StackPane paneSignUp = new StackPane();
        paneSignUp.setPadding(new Insets(100, 100, 100, 100));
        paneSignUp.getChildren().addAll(squareSignUp, groupSignUp);

        Rectangle squareVideoSignUp = new Rectangle(500, 100, 400, 400);
        squareVideoSignUp.setFill(Color.web("#fbf6f5"));
        squareVideoSignUp.setArcHeight(200);
        squareVideoSignUp.setArcWidth(200);
        

        VBox videoSignUp = new VBox();
        videoSignUp.setAlignment(Pos.CENTER);
        videoSignUp.setPadding(new Insets(100, 100, 100, 100));
        videoSignUp.getChildren().add(squareVideo);
        
        
        BorderPane rootSignUp = new BorderPane();
        rootSignUp.setStyle("-fx-background-image: url('abc.jpeg')");
        rootSignUp.setLeft(paneSignUp);
       //rootSignUp.setRight(videoSignUp);
        
        sign_up = new Scene(rootSignUp, 1280, 719);
        
          ////////// sections //////////
         Label lsections = new Label("sections");
         lsections.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #707070;-fx-font-size:30");
         Button Lessons  = new Button("Lessons ");
         Lessons.setStyle("-fx-padding: 15 70 15 70;-fx-background-radius: 50;-fx-font-family: STSong;-fx-text-fill: WHITE;-fx-background-color: #bf9d9e;-fx-font-size:20");
        
         Button Play   = new Button("Play  ");
        Play.setStyle("-fx-padding: 15 80 15 80;-fx-background-radius: 50;-fx-font-family: STSong;-fx-text-fill: WHITE;-fx-background-color: #bf9d9e;-fx-font-size:20");
       
         Button Challenge   = new Button("Challenge  ");
        Challenge.setStyle("-fx-padding: 15 55 15 55;-fx-background-radius: 50;-fx-font-family: STSong;-fx-text-fill: WHITE;-fx-background-color: #bf9d9e;-fx-font-size:20");
         
         Button topten  = new Button("top ten ");
         topten.setStyle("-fx-padding: 15 40 15 40;-fx-background-radius: 50;-fx-font-family: STSong;-fx-text-fill: WHITE;-fx-background-color: #7C7070;-fx-font-size:20");
        
        
          
          
          VBox groupsections = new VBox(25);
          groupsections.setPadding(new Insets(20, 20, 20, 20));
        groupsections.setAlignment(Pos.CENTER);
        groupsections.getChildren().addAll(lsections,Lessons, Play, Challenge, topten);
        
        

          
        Rectangle squareVideosections= new Rectangle(500, 100, 450, 450);
        squareVideosections.setFill(Color.web("#fbf6f5"));
        squareVideosections.setStyle("-fx-background-radius: 50;");
        squareVideosections.setArcHeight(200);
        squareVideosections.setArcWidth(200);
       
        
        StackPane panesections = new StackPane();
        panesections.setPadding(new Insets(100, 100, 100, 100));
        panesections.getChildren().addAll(squareVideosections, groupsections);
        
       
        
        Rectangle squareVideoo = new Rectangle(500, 100, 450, 450);
        squareVideoo.setFill(Color.web("#fbf6f5"));
        squareVideoo.setArcHeight(200);
        squareVideoo.setArcWidth(200);
        MediaPlayer mediaPlayer1 ;
        Media media1 = new Media(new File(path).toURI().toString()); 
        mediaPlayer1 = new MediaPlayer(media);
        MediaView mediaView1 = new MediaView (mediaPlayer);
        mediaView1.setFitHeight(390);
        mediaView1.setFitWidth(390);
        mediaView1.setEffect(dropShadow);
        mediaView1.setStyle("-fx-border-color: black");
        
        ImageView logout=new ImageView(new Image("logout.png"));
        logout.setFitHeight(60);
        logout.setFitWidth(60);
        
        StackPane v = new StackPane();
        v.setPadding(new Insets(100, 100, 100, 100));
        v.getChildren().addAll(squareVideoo,mediaView1);
           
        BorderPane rootsection = new BorderPane();
        rootsection.setStyle("-fx-background-image: url('abc.jpeg')");
        rootsection.setLeft(panesections);
        rootsection.setRight(v);
        rootsection.setBottom(logout);
         
     
        section=new Scene(rootsection, 1270, 712);
        

        
         logout.setOnMousePressed(e ->{
              primaryStage.setScene(log_in);
              primaryStage.setTitle("log in");
        });
////////// actions //////////
       
        ////////// actions //////////
        ////////// actions //////////
        
       
        topten.setOnAction(e-> { primaryStage.setTitle("Top Ten");
                                 primaryStage.setScene(topepage); });
       
        
        signUp.setOnMousePressed(e ->{
            primaryStage.setScene(sign_up);
            primaryStage.setTitle("sign up");
        });
        
        btnSignUp.setOnAction(e ->{
            if(usernameSignUp.getText().equals("") || passwordSignUp.getText().equals("") || emailSignUp.getText().equals("")){
                msg2.setText("all required, plz fill them out");
            }
            else if(passwordSignUp.getText().length() < 8){
                msg2.setText("invalid password, try again");
            }
            else if(!emailSignUp.getText().contains("@")){
                msg2.setText("invalid email, try again");
            }
            else{
                user u = new user();
                int j=0;
                Session s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                
                List <user> list = null;
                String query = "from user";
                Query q = s.createQuery(query);
                list = q.list();
                for(user use : list){
                    if(use.getUserName().equals(usernameSignUp.getText())){
                        msg2.setText("username is used");
                        return;
                    }
                    else
                        j=1;
                }
                t.commit();
                s.close();
                
                s = HibernateUtil.getSessionFactory().openSession();
                t = s.beginTransaction();
                if(boy.isSelected())
                    u.setGender("boy");
                else
                    u.setGender("girl");
                t.commit();
                s.close();
                
                s = HibernateUtil.getSessionFactory().openSession();
                t = s.beginTransaction();
                
                u.setUserName(usernameSignUp.getText());
                u.setEmail(emailSignUp.getText());
                u.setPassword(passwordSignUp.getText());
                s.save(u);
                t.commit();
                s.close();
                
                s = HibernateUtil.getSessionFactory().openSession();
                t = s.beginTransaction();
                Level l = new Level();
                l.setUserName(usernameSignUp.getText());
                l.setLearnLevel(0);
                l.setScores(0);
                l.setChallengeLevel(0);
                s.save(l);
                t.commit();
                s.close();
                primaryStage.setScene(log_in);
            }
        });
        
        btn.setOnAction(e ->{
            int j=0;
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            List <user> list = null;
            String query = "from user";
            Query q = s.createQuery(query);
            list = q.list();
            for(user u : list){
                if(u.getUserName().equals(username.getText())){
                    if(u.getPassword().equals(password.getText())){
                        user_name=username.getText();
                        j=1;
                        primaryStage.setScene(section);
                        return;
                        
                    }
                }
                else{
                    msg.setText("username or password incorrect");
                }
                
            }
            t.commit();
            s.close();
        });

        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run(){
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        primaryStage.setTitle("log in");
        primaryStage.setScene(log_in);
        primaryStage.show();
 
        
  
 //-------------------------------play page-------------------------------------
       
       Rectangle squareimag= new Rectangle(500, 100, 200, 300);
       VBox vbox1 = new VBox(squareimag);
       vbox1.setAlignment(Pos.CENTER_LEFT);
       vbox1.setPadding(new Insets(0, 0, 0, 60));
       Label lplay = new Label("Enter the letter?");
       Label info =new Label();
       Label info2 =new Label("Click SHIFT to To check the letter and move to next level");
       lplay.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #707070;-fx-font-size:15");
       info.setStyle("-fx-font-family: Rockwell ;-fx-text-fill:  #DC143C;-fx-font-size:10");
       info2.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #707070;-fx-font-size:10");
       TextField textplay = new TextField();
       VBox vbox_2 = new VBox(10);
       vbox_2.getChildren().addAll(vbox1,lplay,textplay,info2,info);
       vbox_2.setPadding(new Insets(60, 60, 10, 60));
       Rectangle squarePlay= new Rectangle(500, 100, 450, 450);
       squarePlay.setFill(Color.web("#fbf6f5"));
       squarePlay.setStyle("-fx-background-radius: 50;");
       squarePlay.setArcHeight(200);
       squarePlay.setArcWidth(200);
       StackPane StackPane1 = new StackPane();
       StackPane1.setPadding(new Insets(100, 100, 100, 100));
       StackPane1.getChildren().addAll(squarePlay,vbox_2);
        
        
        Rectangle ScorePlay= new Rectangle(50, 10, 100, 40);
        ScorePlay.setFill(Color.web("#bf9d9e"));
        ScorePlay.setStyle("-fx-background-radius: 50;");
        ScorePlay.setArcHeight(40);
        ScorePlay.setArcWidth(40);
        Label msg3 = new Label();
        msg3.setStyle("-fx-text-fill: white;");
        Label Scoreplay = new Label("my Score:",msg3);
        Scoreplay.setContentDisplay(ContentDisplay.RIGHT);
        Scoreplay.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: white;-fx-font-size:15");
        StackPane Scoreplaypane = new StackPane();
        Scoreplaypane.getChildren().addAll(ScorePlay,Scoreplay);
        
        ImageView cplay=new ImageView(new Image("home-7.png"));
        cplay.setFitHeight(60);
        cplay.setFitWidth(60);
        
        VBox vbox3 = new VBox(Scoreplaypane,cplay);
        vbox3.setSpacing(535);
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setPadding(new Insets(0, 10, 40, 10));
        
        
        Label lplay2 = new Label("Play");
        lplay2.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #bf9d9e;-fx-font-size:15");
        ImageView imageView2=new ImageView(new Image("console.png"));
        imageView2.setFitHeight(60);
        imageView2.setFitWidth(60);
        Circle circleplay = new Circle();
        circleplay.setRadius(60);
        circleplay.setFill(Color.rgb(251, 246, 245, 1.0));
        VBox vbox4 = new VBox(5);
        vbox4.getChildren().addAll(lplay2,imageView2);
        vbox4.setAlignment(Pos.CENTER);
        
        StackPane StackPane_3 = new StackPane();
        StackPane_3.getChildren().addAll(circleplay,vbox4);
        
        
        HBox hbox2=new HBox(StackPane_3);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.setPadding(new Insets(0, 30, 25, 150));
        

        HBox hbox1=new HBox(StackPane1);
        hbox1.setSpacing(15);
        hbox1.setAlignment(Pos.CENTER_LEFT);
        
       
        BorderPane rootplay = new BorderPane();
        rootplay.setStyle("-fx-background-image: url('abc.jpeg')");
        rootplay.setCenter(hbox1);
        rootplay.setRight(vbox3);
        rootplay.setLeft(hbox2);
        
      
      
        
        cplay.setOnMousePressed(e ->{
              primaryStage.setScene(section);
              primaryStage.setTitle("section");
        });
        
       char alphabet[]={'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
       Image im[]={new Image("A.jpg"),new Image("B.jpg"),new Image("C.jpg"),new Image("D.jpg"),new Image("E.jpg"),new Image("F.jpg"),new Image("G.jpg"),new Image("H.jpg"),new Image("I.jpg"),new Image("J.jpg"),new Image("k.jpg"),new Image("L.jpg"),new Image("M.jpg"),new Image("N.jpg"),new Image("O.jpg"),new Image("P.jpg"),new Image("Q.jpg"),new Image("R.jpg"),new Image("S.jpg"),new Image("T.jpg"),new Image("U.jpg"),new Image("V.jpg"),new Image("W.jpg"),new Image("X.jpg"),new Image("Y.jpg"),new Image("Z.jpg")};
    
       
       Playpage=new Scene(rootplay, 1270, 712);
       
       
       Play.setOnAction(e-> {
           
                Session ss = HibernateUtil.getSessionFactory().openSession();
                Transaction tt = ss.beginTransaction(); 
                List <Level> listt = null;
                String queryy = "from Level";
                Query qq = ss.createQuery(queryy);
                listt = qq.list();
                for(Level l : listt)
                if(l.getUserName().equals(user_name))
                    msg3.setText(String.valueOf((l.getScores())));
                tt.commit();
                ss.close();
                
               c = (int)(Math.random()*(25-0+1)+0);
               squareimag.setFill(new ImagePattern(im[c]));
            
           Playpage.setOnKeyPressed(o -> 
          {
            if (o.getCode() == KeyCode.SHIFT)
           {
               info.setText("");
               textplay.setStyle("-fx-background-color: white");
               if(textplay.getText().equalsIgnoreCase(String.valueOf(alphabet[c])))
             { 
                Session s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction(); 
                List <Level> list = null;
                String query = "from Level";
                Query q = s.createQuery(query);
                list = q.list();
                for(Level l : list){
                if(l.getUserName().equals(user_name))
                {    
                    l.setScores((l.getScores())+2);
                    msg3.setText(String.valueOf((l.getScores())));
                }
                }
                
                t.commit();
                s.close();
                c =(int)(Math.random()*(25-0+1)+0);
                squareimag.setFill(new ImagePattern(im[c]));
                textplay.clear();
                
             }
              else if(textplay.getText().trim().isEmpty())
                    info.setText("Please enter the letter");
              else
             { 
                info.setText("");
                textplay.setStyle("-fx-background-color: rgba(255,0,0,0.8);");
                Session s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction(); 
                List <Level> list = null;
                String query = "from Level";
                Query q = s.createQuery(query);
                list = q.list();
                for(Level l : list){
                if(l.getUserName().equals(user_name))
                {
                    if(l.getScores()>0)
                    l.setScores((l.getScores())-2);
                    msg3.setText(String.valueOf((l.getScores())));
                }
                }
                t.commit();
                s.close();
               
             }
             textplay.clear(); 
             
                  
         }
          
        }); 
            primaryStage.setTitle("Play");
            primaryStage.setScene(Playpage); });
      
            
 

//-------------------------------Score page-------------------------------------
      

        ImageView Shome=new ImageView(new Image("home-7.png"));
        Shome.setFitHeight(60);
        Shome.setFitWidth(60);
        
         Shome.setOnMousePressed(e ->{
              primaryStage.setScene(section);
              primaryStage.setTitle("section");
        });
        
        Label Top = new Label("Top Ten");
        Top.setStyle("-fx-font-family: Forte ;-fx-text-fill: #839788 ;-fx-font-size:70");
        ObservableList<String> obTopName = FXCollections.observableArrayList();
        ListView listTopName = new ListView(obTopName);
        listTopName.setPrefSize(250, 500);
        ObservableList<String> obTopScore = FXCollections.observableArrayList();
        ListView listTopScore = new ListView(obTopScore);
        listTopScore.setPrefSize(250, 500);
        
        topten.setOnMousePressed(e ->{
              
            listTopName.getItems().clear();
            listTopScore.getItems().clear();
         Session session = HibernateUtil.getSessionFactory().openSession();
        List<Level> LList = null;
        String queryStr = "from Level";
        Query query = session.createQuery(queryStr);
        LList = query.list();
        session.close();
        
        String[] name = new String[LList.size()];
        int[] score = new int[LList.size()] ;
        int i=0;
        int Duplicate=0;
        
        
        for(Level ss: LList){
            name[i]=ss.getUserName();
            score[i]=ss.getScores();
            i++;
        }
        
        int tmp = 0; 
        String tmp2="";
        for (int j = 0; j < LList.size(); j++) {
           
            for (int k = j+1; k < LList.size(); k++) {
                
                if (score[j] <= score[k])   
                { 
                    if(score[j] == score[k])
                        Duplicate++;
                    
                    tmp = score[j];  
                    score[j] = score[k];  
                    score[k] = tmp;
                    
                    tmp2 = name[j];  
                    name[j] = name[k];  
                    name[k] = tmp2;
                    
                }  
            }
        }
        
        
        for (int j = 0; j < 10+Duplicate; j++) {
            
            obTopName.add(name[j]);
            obTopScore.add(String.valueOf(score[j]));
        }
        
       
        });
        
        HBox lvs = new HBox(10);
        lvs.setAlignment(Pos.CENTER);
        lvs.setMaxWidth(600);
        lvs.setMinWidth(600);
        
        VBox TopName = new VBox(10);
        VBox TopScore = new VBox(10);
        TopName.getChildren().addAll(new Label("User Name: "), new ScrollPane(listTopName));
        TopScore.getChildren().addAll(new Label("Score: "), new ScrollPane(listTopScore));
        lvs.getChildren().addAll(TopName,TopScore);
        
      
        HBox rootTop = new HBox(40);
        
        rootTop.getChildren().addAll(Top,lvs,Shome);
        rootTop.setAlignment(Pos.CENTER_LEFT);
        rootTop.setPadding(new Insets(130, 0, 130, 80));
        rootTop.setStyle("-fx-background-image: url('abc.jpeg')");
        
        topepage = new Scene(rootTop, 1280, 719);
    



        
        /////////Lessons page////////
 String alpha[]={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
       Image imm[]={new Image("A.jpg"),new Image("B.jpg"),new Image("C.jpg"),new Image("D.jpg"),new Image("E.jpg"),new Image("F.jpg"),new Image("G.jpg"),new Image("H.jpg"),new Image("I.jpg"),new Image("J.jpg"),new Image("k.jpg"),new Image("L.jpg"),new Image("M.jpg"),new Image("N.jpg"),new Image("O.jpg"),new Image("P.jpg"),new Image("Q.jpg"),new Image("R.jpg"),new Image("S.jpg"),new Image("T.jpg"),new Image("U.jpg"),new Image("V.jpg"),new Image("W.jpg"),new Image("X.jpg"),new Image("Y.jpg"),new Image("Z.jpg")};
       

       Rectangle hand= new Rectangle(500, 100, 200, 300);
       
       Rectangle but= new Rectangle(120,40,120,40);
       Color c = Color.web("#bf9d9e",1.0);
       but.setFill(c);
       but.setArcHeight(40);
       but.setArcWidth(40);
      
       Label lb = new Label();
       lb.setStyle("-fx-font-size:30px ;-fx-text-fill: white;");
       StackPane stack = new StackPane(but,lb);
       HBox hbox = new HBox(stack);
       hbox.setPadding(new Insets(0,0,0,40));
       VBox vbox2 = new VBox(25,hand,hbox);

       
        Rectangle squareles= new Rectangle(500, 100, 450, 450);
        squareles.setFill(Color.web("#fbf6f5"));
        squareles.setStyle("-fx-background-radius: 50;");
        squareles.setArcHeight(200);
        squareles.setArcWidth(200);
        vbox2.setPadding(new Insets(70, 0, 10, 130));
        
        StackPane sp4 = new StackPane();
        sp4.setPadding(new Insets(100, 100, 100, 100));
        sp4.getChildren().addAll(squareles,vbox2);
        
        Rectangle Scoreles= new Rectangle(50, 10, 80, 40);
        Scoreles.setFill(Color.web("#bf9d9e"));
        Scoreles.setStyle("-fx-background-radius: 50;");
        Scoreles.setArcHeight(40);
        Scoreles.setArcWidth(40);
        Label Scoreless = new Label("Score");
        Scoreless.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: white;-fx-font-size:15");
        StackPane Scorelespane = new StackPane();
        Scorelespane.getChildren().addAll(Scoreles,Scoreless);
        
        Circle circleles = new Circle();
        circleles.setFill(Color.rgb(251, 246, 245, 1.0));
        Label ls2 = new Label("Lessons");
        ls2.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #bf9d9e;-fx-font-size:15");
        ImageView imageles=new ImageView(new Image("online-lesson.png"));
        imageles.setFitHeight(60);
        imageles.setFitWidth(60);
        imageles.setTranslateX(50);
        imageles.setTranslateY(168);
        circleles.setRadius(60);
        circleles.setTranslateX(20);
        circleles.setTranslateY(260);
        ls2.setTranslateX(52);
        ls2.setTranslateY(167);
        VBox sp3 = new VBox(circleles,ls2,imageles);
        
      
        
        ImageView cles=new ImageView(new Image("home-7.png"));
        ImageView rnext=new ImageView(new Image("left-2.png"));
        ImageView rback=new ImageView(new Image("left.png"));
        rnext.setFitHeight(70);
        rnext.setFitWidth(70);
        
        rback.setFitHeight(70);
        rback.setFitWidth(70);
        cles.setFitHeight(60);
        cles.setFitWidth(60);
        cles.setTranslateX(120);
        cles.setTranslateY(270);
        HBox hles=new HBox(rback,sp3,sp4,Scorelespane,cles,rnext);
        hles.setSpacing(35);
        hles.setAlignment(Pos.CENTER);
        Scorelespane.setTranslateX(200);
        Scorelespane.setTranslateY(-300);
        sp4.setTranslateX(10);
        BorderPane rootles = new BorderPane();
        rootles.setStyle("-fx-background-image: url('abc.jpeg')");
        rootles.setCenter(hles);
               
         rnext.setOnMouseClicked(e -> {
                Session s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction(); 
                List <Level> list = null;
                String query2 = "from Level";
                Query q = s.createQuery(query2);
                list = q.list();
                for(Level l : list){
                if(l.getUserName().equals(user_name)){
                    if (e.getButton() == MouseButton.PRIMARY) {
                        l.setLearnLevel(l.getLearnLevel()+1 );
                        hand.setFill(new ImagePattern(imm[l.getLearnLevel()]));
                        lb.setText(alpha[l.getLearnLevel()]);
                        System.out.println(l.getLearnLevel()+"-----------------");
                           if(l.getLearnLevel()==25){
                              l.setLearnLevel(-1);
              }
                }}}
                t.commit();
                s.close();
        });
               
        rback.setOnMouseClicked(e -> {
                Session s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction(); 
                List <Level> list = null;
                String query2 = "from Level";
                Query q = s.createQuery(query2);
                list = q.list();
                for(Level l : list){
                if(l.getUserName().equals(user_name)){        
                    if (e.getButton() == MouseButton.PRIMARY) {
                         l.setLearnLevel(l.getLearnLevel()-1 );
                         hand.setFill(new ImagePattern(imm[l.getLearnLevel()]));
                         lb.setText(alpha[l.getLearnLevel()]);
                         System.out.println(l.getLearnLevel());
                            if(l.getLearnLevel()==0){
                                l.setLearnLevel(26);
                                hand.setFill(new ImagePattern(imm[0]));
                                lb.setText(alpha[0]);
              }
        }}}
                t.commit();
                s.close();
                });
           Lessonspage = new Scene(rootles, 1270, 712);
               

        Lessons.setOnAction(e ->{
                Session s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction(); 
                List <Level> list = null;
                String query2 = "from Level";
                Query q = s.createQuery(query2);
                list = q.list();
                for(Level l : list){
                if(l.getUserName().equals(user_name)){
                   hand.setFill(new ImagePattern(imm[l.getLearnLevel()]));
                   lb.setText(alpha[l.getLearnLevel()]);
                   Scoreless.setText("Score: "+l.getScores());
                }}
                t.commit();
                s.close();
            primaryStage.setScene(Lessonspage);
            primaryStage.setTitle("Lessons page");
        
        });
        
        cles.setOnMousePressed(e ->{
            primaryStage.setScene(section);
        });
        
        
        
        
        //------------------challange Page =----------------------//
        
        
          //the Word
        Rectangle chrectangle1 = new Rectangle(500,120);
        chrectangle1.setArcHeight(100);
        chrectangle1.setArcWidth(100);
        chrectangle1.setFill(Color.web("#bfbbb6"));
        // the challange word
        Label ch1=new Label("J");
        Label ch2= new Label("A");
        Label ch3=new Label("V");
        Label ch4= new Label("A");
        ch1.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
        ch2.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
        ch3.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
        ch4.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
        HBox charlabel = new HBox(12);
        charlabel.getChildren().addAll(ch1,ch2,ch3,ch4);
        charlabel.setAlignment(Pos.CENTER);
        StackPane chstack = new StackPane();
        chstack.getChildren().addAll(chrectangle1,charlabel);
        chstack.setAlignment(Pos.CENTER);
        //label for the hint
        Label chlabel2 = new Label("choose the letter in order");
        chlabel2.setStyle("-fx-font-family: STSong ;-fx-text-fill: #707070;-fx-font-size:20");
         //label of the message
        chwrong.setStyle("-fx-font-family: STSong ;-fx-text-fill: #707070;-fx-font-size:40");
        HBox wro = new HBox(10);
        wro.setAlignment(Pos.CENTER);
        wro.getChildren().addAll(chlabel2,chwrong);
        //icons picture
        Rectangle chrectangle2 = new Rectangle(1000 ,350);
        chrectangle2.setArcHeight(200);
        chrectangle2.setArcWidth(200);
        chrectangle2.setFill(Color.web("#fbf6f5"));
        //defind 26 icon with the height and width
        ImageView imm1[]={new ImageView("A.jpg"),new ImageView("B.jpg"),new ImageView("C.jpg"),new ImageView("D.jpg"),new ImageView("E.jpg"),new ImageView("F.jpg"),new ImageView("G.jpg"),new ImageView("H.jpg"),new ImageView("I.jpg"),new ImageView("J.jpg")
        ,new ImageView("k.jpg"),new ImageView("L.jpg"),new ImageView("M.jpg"),new ImageView("N.jpg"),new ImageView("O.jpg"),new ImageView("P.jpg"),new ImageView("Q.jpg"),new ImageView("R.jpg"),new ImageView("S.jpg"),new ImageView("T.jpg"),new ImageView("U.jpg")
        ,new ImageView("V.jpg"),new ImageView("W.jpg"),new ImageView("X.jpg"),new ImageView("Y.jpg"),new ImageView("Z.jpg")};
       
       for(int i=0 ; i<imm1.length;i++){
       imm1[i].setFitHeight(90);
       imm1[i].setFitWidth(90);
       }
        
        //collecting all icons
        HBox chHbox1 = new HBox(15);
        chHbox1.getChildren().addAll(imm1[12],imm1[1],imm1[10],imm1[3],imm1[19],imm1[8],imm1[15],imm1[21],imm1[23]);
        chHbox1.setAlignment(Pos.CENTER);
        HBox chHbox2 = new HBox(15);
        chHbox2.getChildren().addAll(imm1[0],imm1[6],imm1[16],imm1[18],imm1[22],imm1[5],imm1[24],imm1[9],imm1[13]);
        chHbox2.setAlignment(Pos.CENTER);
        HBox chHbox3 = new HBox(15);
        chHbox3.getChildren().addAll(imm1[11],imm1[4],imm1[14],imm1[2],imm1[17],imm1[7],imm1[20],imm1[25]);
        chHbox3.setAlignment(Pos.CENTER);
        VBox chVbox2 = new VBox(15);
        chVbox2.setAlignment(Pos.CENTER);
        chVbox2.getChildren().addAll(chHbox1,chHbox2,chHbox3);
        StackPane chstack1 = new StackPane();
        chstack1.getChildren().addAll(chrectangle2,chVbox2);
       
        
        //score pane
         Rectangle Scoreles1= new Rectangle(50, 10, 100, 40);
        Scoreles1.setFill(Color.web("#bf9d9e"));
        Scoreles1.setStyle("-fx-background-radius: 50;");
        Scoreles1.setArcHeight(40);
        Scoreles1.setArcWidth(40);
        Label chscore = new Label();
        chscore.setStyle("-fx-font-family: STSong ;-fx-text-fill: #707070;-fx-font-size:20");
        Label Scoreless1 = new Label("Score",chscore);
        Scoreless1.setContentDisplay(ContentDisplay.RIGHT);
        Scoreless1.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: white;-fx-font-size:15");
        StackPane Scorelespane1 = new StackPane();
        Scorelespane1.getChildren().addAll(Scoreles1,Scoreless1);
        
        
        //timer number
        Label lbtimer = new Label("60");
       
        
        StackPane timerpane = new StackPane();
        Circle ctime = new Circle(50);
        ctime.setFill(Color.WHITE);
        timerpane.getChildren().addAll(ctime,lbtimer);
        timerpane.setAlignment(Pos.CENTER);
        
        //home circle
        ImageView chhome =new ImageView(new Image("home-7.png"));
        chhome.setFitHeight(75);
        chhome.setFitWidth(75);
        
        
       //put All right elements together
       VBox chroo = new VBox(380);
       chroo.getChildren().addAll(timerpane,chhome);
       chroo.setAlignment(Pos.CENTER);
       
       VBox chroot = new VBox(20);
       chroot.getChildren().addAll(Scorelespane1,chroo);
       chroot.setAlignment(Pos.CENTER);
       
        //put all center elements together
        VBox chroot1 = new VBox(10);
        chroot1.getChildren().addAll(chstack,wro,chstack1);
        chroot1.setPadding(new Insets(120, 0, 0, 0));
        
        HBox chborder= new HBox(15);
        chborder.getChildren().addAll(chroot1,chroot);
        chborder.setPadding(new Insets(0,0,0,70));
        //تغيير الباث 
        //sound for the last 10 seconds
        String soundpath="C:\\Users\\lulu-\\Desktop\\project-AP\\abcsign\\src\\10seconds.mp4";
        Media sound = new Media(new File(soundpath).toURI().toString());
        MediaPlayer soundplayer = new MediaPlayer(sound);
        
        //the background image
        chborder.setStyle("-fx-background-image: url('abc.jpeg')");
        //the final root
        challange = new Scene(chborder,1270 ,712);
        //The End of Styling
        
        
        
         
        //Start The action:
        
         Challenge.setOnAction(e-> {
        primaryStage.setTitle("challange");
        primaryStage.setScene(challange);
        lbtimer.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #C99DA3;-fx-font-size:60");
        timer1=60;
          //animation
        tm = new Timeline(new KeyFrame(Duration.seconds(1),t->{
        lbtimer.setText(timer1+"");
        timer1--;
        //warning
         if(timer1<10 & timer1>0){
        lbtimer.setStyle("-fx-font-family: Rockwell ; fx-font-weight: bold ;-fx-text-fill: #8B0000 ;-fx-font-size:70");
        //turn ou the warning sound
        soundplayer.play();
           }
         if(timer1<0){ //if time is finished before complete all letters
            soundplayer.stop();
            lbtimer.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #C99DA3;-fx-font-size:60");
            timer1=60; 
            chwrong.setText("Time is Up Tray Again"); //message to confirm the player that the time is finished
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished( dd1 -> chwrong.setText(null)
            );
            pause.play();
            ch1.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
            ch2.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
            ch3.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
            ch4.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
            if(chletter<=3){
            chletter=0 ; 
            }
        }
        }));
        tm.setCycleCount(Animation.INDEFINITE);
        tm.playFrom(STYLESHEET_MODENA); 
          //take the level from DB
                Session s5 = HibernateUtil.getSessionFactory().openSession();
                List <Level> list5 =null;
                String query5 = "from Level";
                Query q5 = s5.createQuery(query5);
                list5 = q5.list();
                s5.close();
                for(Level l5 : list5){
                if(l5.getUserName().equals(user_name)){
                   chlevel = l5.getChallengeLevel();
                      if(chlevel==1){
                         ch1.setText("L"); ch2.setText("I");ch3.setText("F"); ch4.setText("E");
                }
                else if(chlevel==2){
                     ch1.setText("H"); ch2.setText("U");ch3.setText("N"); ch4.setText("T");
                 }
                }}
                
                        
         //take the scores from DB
           Session s1 = HibernateUtil.getSessionFactory().openSession(); 
                        List <Level> list1 = null;
                        Query q1 = s1.createQuery("from Level");
                        list1 = q1.list();
                         s1.close();
                        for(Level l1 : list1){
                        if(l1.getUserName().equals(user_name))
                         {
                            chscore.setText(String.valueOf((l1.getScores())));
                        }
                        }
                        
         
         
         
         });
         
         
         
         
           
        //go to the section page
        chhome.setOnMouseClicked(e ->{
            primaryStage.setScene(section);
            tm.stop();
            soundplayer.stop();
        });
            
        
        
        
            //when choose the wrong letter
            
            for(int i=1 ; i<imm1.length; i++){
               imm1[i].setOnMouseClicked( e -> { 
                     wrongchoice();
              });
            }
           
            //the right letters
               //java
               //J
            imm1[9].setOnMouseClicked( e -> { 
                      if(chletter==0 & chlevel==0){
             ch1.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80");  
              chletter++;
              }
                else{
                     wrongchoice();
                }
            });
            //A
            imm1[0].setOnMouseClicked( e -> { 
                        if(chletter==1 & chlevel==0){
                            ch2.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80");  
                             chletter++;
                          
              }
                        else if(chletter==3 & chlevel==0){
                            ch4.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80");  
                            chletter=0;
                            chlevel++;
                            chwrong.setText("Congratulation You finished the 1st Level"); 
                            PauseTransition pause = new PauseTransition(Duration.seconds(4));
                            pause.setOnFinished( dd1 -> chwrong.setText(null));
                            pause.play();
                            PauseTransition pause1 = new PauseTransition(Duration.seconds(4)); 
                            pause1.setOnFinished( dd1 -> {ch1.setText("L"); ch2.setText("I");ch3.setText("F"); ch4.setText("E");
                            ch1.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
                            ch2.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
                            ch3.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
                            ch4.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
                             timer1=60;
         
                             
                         });
                             pause1.play(); 
                             
                        //increase the challange level in DB
        
                         Session s6 = HibernateUtil.getSessionFactory().openSession();
                         Transaction t6 = s6.beginTransaction(); 
                         List <Level> list6 = null;
                         String query6 = "from Level";
                         Query q6 = s6.createQuery(query6);
                         list6 = q6.list();
                         for(Level l6 : list6){
                            if(l6.getUserName().equals(user_name)){
                                 l6.setChallengeLevel(1);
                             }}
                         t6.commit();
                         s6.close();
                         
                         //increase the score with 5 points
                         
                        Session s = HibernateUtil.getSessionFactory().openSession();
                        Transaction t = s.beginTransaction(); 
                        List <Level> list = null;
                        Query q = s.createQuery("from Level");
                        list = q.list();
                        for(Level l : list){
                        if(l.getUserName().equals(user_name))
                         {
                         l.setScores((l.getScores())+5);
                            chscore.setText(String.valueOf((l.getScores())));
                        }
                        }
                        t.commit();
                        s.close();
                        }
                         else{
                            wrongchoice();
                            }
                            });
            
            //V
            imm1[21].setOnMouseClicked( e -> { 
                        if(chletter==2 & chlevel==0){
                            ch3.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80");  
                            chletter++;
              }
                else{
                     wrongchoice();
                }
            });
            
            //L
            imm1[11].setOnMouseClicked( e -> { 
                if(chletter==0 & chlevel==1){
             ch1.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80");  
              chletter++;
              }
                else{
                     wrongchoice();
                }
               
            });
            
            //I
            imm1[8].setOnMouseClicked( e -> { 
                if(chletter==1 & chlevel==1){
            ch2.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80");  
              chletter++;
              }
              else{
                wrongchoice();
                }
            });
            
            //F
            imm1[5].setOnMouseClicked( e -> { 
                if(chletter==2 & chlevel==1){
                    ch3.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80"); 
                    chletter++;}
                else{
                     wrongchoice();
                }
                
            });
            
            //E
            imm1[4].setOnMouseClicked( e -> { 
                if(chletter==3 & chlevel==1){
                    ch4.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80");  
                    chletter=0;
                    chlevel++;
                     chwrong.setText("Congratulation You finished the 2nd Level"); 
                      PauseTransition pause = new PauseTransition(Duration.seconds(4));
                     pause.setOnFinished( dd1 -> chwrong.setText(null));
                     pause.play();
                     PauseTransition pause1 = new PauseTransition(Duration.seconds(4));
                     pause1.setOnFinished( dd1 -> {ch1.setText("H"); ch2.setText("U");ch3.setText("N"); ch4.setText("T");
                     ch1.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
                     ch2.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
                     ch3.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
                     ch4.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #F5E4D7;-fx-font-size:80");
                     timer1=60;
                    });
                     pause1.play();
                     //level++
                     Session s6 = HibernateUtil.getSessionFactory().openSession();
                     Transaction t6 = s6.beginTransaction(); 
                        List <Level> list6 = null;
                        String query6 = "from Level";
                        Query q6 = s6.createQuery(query6);
                        list6 = q6.list();
                        for(Level l6 : list6){
                     if(l6.getUserName().equals(user_name)){
                         l6.setChallengeLevel(2);
                        }}
                     t6.commit();
                     s6.close();
                    
                     //increase the score by 5 points
                     
                      Session s = HibernateUtil.getSessionFactory().openSession();
                        Transaction t = s.beginTransaction(); 
                        List <Level> list = null;
                        Query q = s.createQuery("from Level");
                        list = q.list();
                        for(Level l : list){
                        if(l.getUserName().equals(user_name))
                         {
                         l.setScores((l.getScores())+5);
                            chscore.setText(String.valueOf((l.getScores())));
                        }
                        }
                        t.commit();
                        s.close();
                }
                else{
                     wrongchoice();
                }
            });
           
            //H
         imm1[7].setOnMouseClicked( e1 -> { 
                if(chletter==0 & chlevel==2){
             ch1.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80");  
              chletter++;}
                else{
                     wrongchoice();
                }
            });
         
         //U
         imm1[20].setOnMouseClicked( e1 -> { 
                if(chletter==1 & chlevel==2){
            ch2.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80");  
              chletter++;
              }
                else{
                     wrongchoice();
                }
            });
         
         //N
            imm1[13].setOnMouseClicked( e1 -> { 
                if(chletter==2 & chlevel==2){
                    ch3.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80"); 
                    chletter++;}
                else{
                     wrongchoice();
                }
            });
            
            //T
             imm1[19].setOnMouseClicked( e1 -> { 
                if(chletter==3 & chlevel==2){
              ch4.setStyle("-fx-font-family: Rockwell ;-fx-text-fill: #000000;-fx-font-size:80"); 
              chwrong.setText("Congratulation You finished all challanges"); 
              PauseTransition pause = new PauseTransition(Duration.seconds(4));
              pause.setOnFinished( dd1 -> chwrong.setText(null));
              pause.play();
                
              
                     //increas the score by 5 points
                     
                      Session s = HibernateUtil.getSessionFactory().openSession();
                        Transaction t = s.beginTransaction(); 
                        List <Level> list = null;
                        Query q = s.createQuery("from Level");
                        list = q.list();
                        for(Level l : list){
                        if(l.getUserName().equals(user_name))
                         {
                         l.setScores((l.getScores())+5);
                            chscore.setText(String.valueOf((l.getScores())));
                        }
                        }
                        t.commit();
                        s.close();
                }
                
                else{
                     wrongchoice();
                }
            }
             );
         
        primaryStage.show();
     
    }
    public static void main(String[] args) {
        launch(args);
    }   
}
