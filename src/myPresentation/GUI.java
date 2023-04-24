package myPresentation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;


public class GUI extends JFrame {
    //atributos
    private JButton myPhoto, myHobby, myExpectations;
    private JPanel containerButtons, containerImage;
    private Listener listener;
    private Title title;
    private JLabel imageLabel;
    private JTextArea expectativesText;

    //metodos
    public GUI(){
        initGUI();

        this.setTitle("My Presentation");
        this.setSize(600, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        //Definir container y Layout del JFrame
        //Crear objetos Escucha y Control
        //Configurar JComponents

        Font fuente = new Font("Impac",Font.BOLD,12);
        title = new Title("A little more about me", Color.BLACK);
        myPhoto = new JButton("This is me");
        myHobby = new JButton("This is my passion");
        myExpectations = new JButton("I expect to get the best of you");
        expectativesText = new JTextArea();
        expectativesText.setEditable(false);

        containerButtons = new JPanel();
        containerImage = new JPanel();

        listener = new Listener();

        imageLabel = new JLabel();



       //Cambio de color de los elementos:
        containerButtons.setBackground(Color.black);
        containerImage.setBackground(Color.RED.brighter());

        myPhoto.setBackground(Color.yellow);
        myHobby.setBackground(Color.green.darker());
        myExpectations.setBackground(Color.yellow);

        //Cambio el tipo de letra:
        title.setFont(fuente);
        myPhoto.setFont(fuente);
        myHobby.setFont(fuente);
        myExpectations.setFont(fuente);

        Color colorContenedorImagenes = Color.black;

        containerImage.setBorder(BorderFactory.createTitledBorder(null, "About me", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font(Font.SANS_SERIF,Font.PLAIN,20), Color.BLACK));
        containerImage.add(imageLabel);
        containerButtons.setCursor(new Cursor(Cursor.HAND_CURSOR));

        containerButtons.add(myPhoto);
        containerButtons.add(myHobby);
        containerButtons.add(myExpectations);

        myPhoto.addActionListener(listener);
        myHobby.addActionListener(listener);
        myExpectations.addActionListener(listener);

        this.add(title, BorderLayout.NORTH);
        this.add(containerButtons, BorderLayout.SOUTH);
        this.add(containerImage, BorderLayout.CENTER);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Verificar si la tecla presionada es 'm' o 'M'
                if (e.getKeyChar() == 'm' || e.getKeyChar() == 'M') {
                    // Hacer lo mismo que ocurre al presionar el botón "myExpectations"
                    expectativesText.setText("I expect to get the best of everyone of you \n" +
                            "My contact is carlos.felipe.montoya@correounivalle.edu.co");
                    expectativesText.setBackground(null);
                    expectativesText.setForeground(Color.BLACK);
                    containerImage.add(expectativesText);
                    revalidate();
                    repaint();
                }
            }
        });
        this.setFocusable(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI myGui = new GUI();
            }
        });
    }


    private class Listener implements ActionListener{
        int contadorClicks = 0;
        private ImageIcon image;

        private String textExpectatives;

        @Override
        public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(null, "Press button");
            imageLabel.setIcon(null);
            containerImage.remove(expectativesText);
            textExpectatives="Mi expectativa con el curso es aprender a trabajar con la programacion orientada\na eventos, ademas del lenguaje JAVA, por que los considero muy importante para el \ndesarrollo de mis habilidades y en mi futura profesion.";

            if(e.getSource() == myPhoto) {
                this.image = new ImageIcon(getClass().getResource("/resources/Me.jpg"));
                containerImage.setBackground(Color.BLACK);
                imageLabel.setIcon(image);

            }
            else if(e.getSource() == myHobby){
                contadorClicks++;
                if (contadorClicks==2) {
                this.image = new ImageIcon(getClass().getResource("/resources/Hobby.jpg"));
                containerImage.setBackground(Color.BLACK);
                imageLabel.setIcon(image);
                contadorClicks=0;
                }
            }
            else if(e.getSource() == myExpectations) {

                expectativesText.setText(textExpectatives);
                expectativesText.setBackground(null);
                containerImage.setBackground(Color.BLACK);
                expectativesText.setForeground(Color.RED.brighter());
                containerImage.add(expectativesText);
            }

            revalidate();
            repaint();
        }


    }

}