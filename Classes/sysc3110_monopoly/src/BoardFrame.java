import org.json.JSONArray;
import org.json.JSONObject;

import Models.JSONHelper;
import Models.Board;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BoardFrame extends JFrame {

    // get DEFAULT_LANGUAGE from board.java
    String language = Board.DEFAULT_LANGUAGE;
    private JSONHelper jsonHelper;

    public BoardFrame() {
        try {
            this.jsonHelper = new JSONHelper(language);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray tiles = this.jsonHelper.getTiles();
        // Setup the Layout
        GridBagLayout boardLayout = new GridBagLayout();
        boardLayout.rowWeights = new double[] { 0.2, 0.1, 0.1, 0.1, 0.1,
                0.1, 0.1, 0.1, 0.1, 0.1, 0.2 };
        boardLayout.columnWeights = new double[] { 0.2, 0.1, 0.1, 0.1, 0.1,
                0.1, 0.1, 0.1, 0.1, 0.1, 0.2 };
        getContentPane().setLayout(boardLayout);

        // Default Grid values
        int gridX = 0;
        int gridY = 0;
        // Add Panels for Each of the four sides
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 11; i++) {
                for (int k = 0; k < tiles.length(); k++) {
                    JSONObject obj = tiles.getJSONObject(k);
                    String name = (String) obj.get("name");
                    Integer id = (Integer) obj.get("id");
                    Integer price = (Integer) obj.get("price");

                    String thePrice = String.valueOf(price);

                    JLabel tileName = new JLabel();
                    JLabel tilePrice = new JLabel();
                    tileName.setFont(new Font("Verdana", Font.BOLD, 10));
                    tilePrice.setFont(new Font("Verdana", Font.BOLD, 10));

                    Box box = Box.createVerticalBox();

                    JPanel redColorPanel = new JPanel();
                    redColorPanel.setBackground(new Color(255, 0, 0));

                    JPanel yellowColorPanel = new JPanel();
                    yellowColorPanel.setBackground(new Color(255, 255, 0));

                    JPanel greenColorPanel = new JPanel();
                    greenColorPanel.setBackground(new Color(0, 128, 0));

                    JPanel darkBlueColorPanel = new JPanel();
                    darkBlueColorPanel.setBackground(new Color(0, 0, 255));

                    JPanel brownColorPanel = new JPanel();
                    brownColorPanel.setBackground(new Color(139, 69, 19));

                    JPanel lightBlueColorPanel = new JPanel();
                    lightBlueColorPanel.setBackground(new Color(135, 206, 235));

                    JPanel purpleColorPanel = new JPanel();
                    purpleColorPanel.setBackground(new Color(128, 0, 128));

                    JPanel orangeColorPanel = new JPanel();
                    orangeColorPanel.setBackground(new Color(255, 128, 0));

                    JPanel tempPanel = new JPanel();

                    tempPanel.add(box);

                    switch (j) {
                        case 0:// Top Spaces
                            if (id == 20) {// Free Parking
                                gridX = 0;
                                gridY = 0;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            if (id == 21) {// Strand
                                gridX = 1;
                                gridY = 0;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(redColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 22) {// Chance
                                gridX = 2;
                                gridY = 0;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            if (id == 23) {// Fleet
                                gridX = 3;
                                gridY = 0;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(redColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 24) {// Trafalgar
                                gridX = 4;
                                gridY = 0;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(redColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 25) {// Fenchurch
                                gridX = 5;
                                gridY = 0;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 26) {// Leicester
                                gridX = 6;
                                gridY = 0;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(yellowColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 27) {// Coventry
                                gridX = 7;
                                gridY = 0;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(yellowColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 28) {// Water
                                gridX = 8;
                                gridY = 0;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 29) {// Piccadilly
                                gridX = 9;
                                gridY = 0;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(yellowColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 30) {// Go to jail
                                gridX = 10;
                                gridY = 0;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            break;
                        case 1:// Left Spaces
                            if (id == 20) {// Free Parking
                                gridX = 0;
                                gridY = 0;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            // gridX = 0;
                            // gridY = i;
                            if (id == 10) {// Jail
                                gridX = 0;
                                gridY = 10;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            if (id == 11) {// Pall
                                gridX = 0;
                                gridY = 9;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(purpleColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 12) {// Electric
                                gridX = 0;
                                gridY = 8;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 13) {// Whitehall
                                gridX = 0;
                                gridY = 7;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(purpleColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 14) {// Northumberland
                                gridX = 0;
                                gridY = 6;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(purpleColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 15) {// Marlyebone
                                gridX = 0;
                                gridY = 5;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 16) {// Bow
                                gridX = 0;
                                gridY = 4;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(orangeColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 17) {// Chest
                                gridX = 0;
                                gridY = 3;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            if (id == 18) {// Marlborough
                                gridX = 0;
                                gridY = 2;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(orangeColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 19) {// Vine
                                gridX = 0;
                                gridY = 1;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(orangeColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            break;
                        case 2:// Right Spaces
                            if (id == 31) {// Regent
                                gridX = 10;
                                gridY = 1;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(greenColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 32) {// Oxford
                                gridX = 10;
                                gridY = 2;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(greenColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 33) {// Chest
                                gridX = 10;
                                gridY = 3;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            if (id == 34) {// Bond
                                gridX = 10;
                                gridY = 4;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(greenColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 35) {// Liverpool
                                gridX = 10;
                                gridY = 5;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 36) {// Chance
                                gridX = 10;
                                gridY = 6;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            if (id == 37) {// Park
                                gridX = 10;
                                gridY = 7;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(darkBlueColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 38) {// Tax
                                gridX = 10;
                                gridY = 8;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            if (id == 39) {// Mayfair
                                gridX = 10;
                                gridY = 9;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(darkBlueColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            break;
                        case 3:// Bottom Spaces
                            if (id == 0) {// GO
                                gridX = 10;
                                gridY = 10;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            if (id == 1) {// Old Kent
                                gridX = 9;
                                gridY = 10;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(brownColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 2) {// Chest
                                gridX = 8;
                                gridY = 10;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            if (id == 3) {// Whitechapel
                                gridX = 7;
                                gridY = 10;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(brownColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 4) {// Tax
                                gridX = 6;
                                gridY = 10;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 5) {// Kings Station
                                gridX = 5;
                                gridY = 10;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 6) {// Angel Islignton
                                gridX = 4;
                                gridY = 10;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(lightBlueColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 7) {// Chance
                                gridX = 3;
                                gridY = 10;
                                tileName = new JLabel(name);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(tileName);
                            }
                            if (id == 8) {// Euston
                                gridX = 2;
                                gridY = 10;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(lightBlueColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            if (id == 9) {// Pentonville
                                gridX = 1;
                                gridY = 10;
                                tileName = new JLabel(name);
                                tilePrice = new JLabel(thePrice);

                                tileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                                tilePrice.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                                box.add(lightBlueColorPanel);
                                box.add(tileName);
                                box.add(tilePrice);
                            }
                            break;
                    }

                    getContentPane().add(tempPanel,
                            new GridBagConstraints(gridX, // XGridSpot
                                    gridY, // YGridSpot
                                    1, // XGridSpaces
                                    1, // YGridSpaces
                                    0.0, 0.0, GridBagConstraints.CENTER,
                                    GridBagConstraints.BOTH, // Fill
                                    new Insets(0, 0, 0, 0), 0, 0));
                    tempPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
            }
        }

        {// Main Inner Area Notice Starts at (1,1) and takes up 11x11
            /*
             * JLabel imageLabel = new JLabel(new
             * ImageIcon("./diagrams/monopoly board center.png"));
             * imageLabel.setHorizontalAlignment(JLabel.CENTER);
             * imageLabel.setVerticalAlignment(JLabel.CENTER);
             * imageLabel.setBorder(new LineBorder(Color.BLACK));
             * 
             */

            {// Main.Main Inner Area Notice Starts at (1,1) and takes up 11x11

                JLabel innerLabel = new JLabel("Scuffed Monopoly");
                JPanel innerPanel = new JPanel();
                innerPanel.add(innerLabel, JPanel.CENTER_ALIGNMENT);

                // innerPanel.add(imageLabel);

                getContentPane().add(
                        innerPanel,
                        new GridBagConstraints(1,
                                1,
                                11,
                                11,
                                0.0, 0.0,
                                GridBagConstraints.CENTER,
                                GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));
            }
        }
    }
}
