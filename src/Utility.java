

public class Utility {
    DataManager dm;

    public Utility(DataManager dm){
        this.dm = dm;
    }

    void imageRuleNeighborsPeriodic(int x) {

        for(int wi = 0; wi < dm.width; wi++){
            for(int hi = 0; hi < dm.height; hi++){
                dm.pixelLast[wi][hi]=dm.img.getRaster().getSample(wi,hi,0);

            }
        }

        String temp = Integer.toBinaryString(x);
        while (temp.length() != 8) {
            temp = "0" + temp;
        }

        char[] znaki = new char[temp.length()];
        int[] rules = new int[temp.length()];


        temp.getChars(0, temp.length(), znaki, 0);

        for (int a = 0; a < temp.length(); a++) {
            rules[a] = Character.getNumericValue(znaki[a]);
            if (rules[a]==1){rules[a]=0;}
            else{rules[a]=255;}
        }


        int i = 1;

        int[] pixelRow = new int[dm.width];


        for (int hi = 0; hi <= dm.height; hi++) {

            if (i == dm.height) { break; }

            for (int j = 0; j < dm.width; j++) {
                pixelRow[j] = dm.pixelLast[j][hi];
            }

            for (int z = 0; z < pixelRow.length; z++) {

                int sasiadLewy = z - 1, sasiadPrawy = z + 1;

                if (z == 0) { sasiadLewy = pixelRow.length-1; }
                else if (z == pixelRow.length - 1) { sasiadPrawy = 0; }

                if (pixelRow[sasiadLewy] == 0 && pixelRow[z] == 0 && pixelRow[sasiadPrawy] == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[0]);
                } else if (pixelRow[sasiadLewy] == 0 && pixelRow[z] == 0 && pixelRow[sasiadPrawy] == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[1]);
                } else if (pixelRow[sasiadLewy] == 0 && pixelRow[z] == 255 && pixelRow[sasiadPrawy] == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[2]);
                } else if (pixelRow[sasiadLewy] == 0 && pixelRow[z] == 255 && pixelRow[sasiadPrawy] == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[3]);
                } else if (pixelRow[sasiadLewy] == 255 && pixelRow[z] == 0 && pixelRow[sasiadPrawy] == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[4]);
                } else if (pixelRow[sasiadLewy] == 255 && pixelRow[z] == 0 && pixelRow[sasiadPrawy] == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[5]);
                } else if (pixelRow[sasiadLewy] == 255 && pixelRow[z] == 255 && pixelRow[sasiadPrawy] == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[6]);
                } else if (pixelRow[sasiadLewy] == 255 && pixelRow[z] == 255 && pixelRow[sasiadPrawy] == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[7]);
                }
            }

            for (int wi = 0; wi < dm.width; wi++) {
                dm.pixelLast[wi][i] = dm.img.getRaster().getSample(wi, i, 0);
            }

            i++;
        }
    }

    void imageRuleNeighborsAreBlack(int x) {

        for (int wi = 0; wi < dm.width; wi++) {
            for (int hi = 0; hi < dm.height; hi++) {
                dm.pixelLast[wi][hi] = dm.img.getRaster().getSample(wi, hi, 0);

            }
        }

        String temp = Integer.toBinaryString(x);
        while (temp.length() != 8) {
            temp = "0" + temp;
        }

        char[] znaki = new char[temp.length()];
        int[] rules = new int[temp.length()];


        temp.getChars(0, temp.length(), znaki, 0);

        for (int a = 0; a < temp.length(); a++) {
            rules[a] = Character.getNumericValue(znaki[a]);
            if (rules[a] == 1) {rules[a] = 0;}
            else { rules[a] = 255;}
        }


        int i = 1;

        int[] pixelRow = new int[dm.width];


        for (int hi = 0; hi <= dm.height; hi++) {

            if (i == dm.height) {break; }

            for (int j = 0; j < dm.width; j++) {
                pixelRow[j] = dm.pixelLast[j][hi];
            }

            for (int z = 0; z < pixelRow.length; z++) {

                int sasiadLewy = 0, sasiadPrawy = 0;

                if (z!=0 && z!= pixelRow.length-1)
                {
                    sasiadLewy = pixelRow[z - 1];
                    sasiadPrawy = pixelRow[z + 1];
                }

                else if(z==0){sasiadLewy=0; sasiadPrawy=pixelRow[z+1];}
                else if(z== pixelRow.length-1){sasiadLewy=pixelRow[z-1];sasiadPrawy=0;}


                if (sasiadLewy == 0 && pixelRow[z] == 0 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[0]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 0 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[1]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 255 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[2]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 255 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[3]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 0 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[4]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 0 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[5]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 255 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[6]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 255 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[7]);
                }
            }


            for (int wi = 0; wi < dm.width; wi++) {
                dm.pixelLast[wi][i] = dm.img.getRaster().getSample(wi, i, 0);
            }

            i++;
        }
    }

    void imageRuleNeighborsAreWhite(int x) {

        for (int wi = 0; wi < dm.width; wi++) {
            for (int hi = 0; hi < dm.height; hi++) {
                dm.pixelLast[wi][hi] = dm.img.getRaster().getSample(wi, hi, 0);

            }
        }

        String temp = Integer.toBinaryString(x);
        while (temp.length() != 8) {
            temp = "0" + temp;
        }

        char[] znaki = new char[temp.length()];
        int[] rules = new int[temp.length()];


        temp.getChars(0, temp.length(), znaki, 0);

        for (int a = 0; a < temp.length(); a++) {
            rules[a] = Character.getNumericValue(znaki[a]);
            if (rules[a] == 1) {rules[a] = 0;}
            else { rules[a] = 255;}
        }


        int i = 1;

        int[] pixelRow = new int[dm.width];


        for (int hi = 0; hi <= dm.height; hi++) {

            if (i == dm.height) {break; }

            for (int j = 0; j < dm.width; j++) {
                pixelRow[j] = dm.pixelLast[j][hi];
            }

            for (int z = 0; z < pixelRow.length; z++) {

                int sasiadLewy = 0, sasiadPrawy = 0;

                if (z!=0 && z!= pixelRow.length-1)
                {
                    sasiadLewy = pixelRow[z - 1];
                    sasiadPrawy = pixelRow[z + 1];
                }

                else if(z==0){sasiadLewy=255; sasiadPrawy=pixelRow[z+1];}
                else if(z== pixelRow.length-1){sasiadLewy=pixelRow[z-1];sasiadPrawy=255;}


                if (sasiadLewy == 0 && pixelRow[z] == 0 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[0]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 0 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[1]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 255 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[2]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 255 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[3]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 0 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[4]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 0 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[5]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 255 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[6]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 255 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[7]);
                }
            }


            for (int wi = 0; wi < dm.width; wi++) {
                dm.pixelLast[wi][i] = dm.img.getRaster().getSample(wi, i, 0);
            }

            i++;
        }
    }


    void animateRuleNeighborsPeriodic(int x) {

        for(int wi = 0; wi < dm.width; wi++){
            for(int hi = 0; hi < dm.height; hi++){
                dm.pixelLast[wi][hi]=dm.img.getRaster().getSample(wi,hi,0);

            }
        }

        String temp = Integer.toBinaryString(x);
        while (temp.length() != 8) {
            temp = "0" + temp;
        }

        char[] znaki = new char[temp.length()];
        int[] rules = new int[temp.length()];


        temp.getChars(0, temp.length(), znaki, 0);

        for (int a = 0; a < temp.length(); a++) {
            rules[a] = Character.getNumericValue(znaki[a]);
            if (rules[a]==1){rules[a]=0;}
            else{rules[a]=255;}
        }


        int i = 1;

        int[] pixelRow = new int[dm.width];


            for (int hi = 0; hi <= dm.height; hi++) {

                if (i == dm.height) { i=1; hi=1; }

                for (int j = 0; j < dm.width; j++) {
                    pixelRow[j] = dm.pixelLast[j][hi];
                }

                for (int z = 0; z < pixelRow.length; z++) {

                    int sasiadLewy = z - 1, sasiadPrawy = z + 1;

                    if (z == 0) { sasiadLewy = pixelRow.length-1; }
                    else if (z == pixelRow.length - 1) { sasiadPrawy = 0; }

                        if (pixelRow[sasiadLewy] == 0 && pixelRow[z] == 0 && pixelRow[sasiadPrawy] == 0) {
                            dm.img.getRaster().setSample(z, i, 0, rules[0]);
                        } else if (pixelRow[sasiadLewy] == 0 && pixelRow[z] == 0 && pixelRow[sasiadPrawy] == 255) {
                            dm.img.getRaster().setSample(z, i, 0, rules[1]);
                        } else if (pixelRow[sasiadLewy] == 0 && pixelRow[z] == 255 && pixelRow[sasiadPrawy] == 0) {
                            dm.img.getRaster().setSample(z, i, 0, rules[2]);
                        } else if (pixelRow[sasiadLewy] == 0 && pixelRow[z] == 255 && pixelRow[sasiadPrawy] == 255) {
                            dm.img.getRaster().setSample(z, i, 0, rules[3]);
                        } else if (pixelRow[sasiadLewy] == 255 && pixelRow[z] == 0 && pixelRow[sasiadPrawy] == 0) {
                            dm.img.getRaster().setSample(z, i, 0, rules[4]);
                        } else if (pixelRow[sasiadLewy] == 255 && pixelRow[z] == 0 && pixelRow[sasiadPrawy] == 255) {
                            dm.img.getRaster().setSample(z, i, 0, rules[5]);
                        } else if (pixelRow[sasiadLewy] == 255 && pixelRow[z] == 255 && pixelRow[sasiadPrawy] == 0) {
                            dm.img.getRaster().setSample(z, i, 0, rules[6]);
                        } else if (pixelRow[sasiadLewy] == 255 && pixelRow[z] == 255 && pixelRow[sasiadPrawy] == 255) {
                            dm.img.getRaster().setSample(z, i, 0, rules[7]);
                        }
                }

                for (int wi = 0; wi < dm.width; wi++) {
                    dm.pixelLast[wi][i] = dm.img.getRaster().getSample(wi, i, 0);
                }

                i++;
            }
        }

    void animateRuleNeighborsAreBlack(int x) {

        for (int wi = 0; wi < dm.width; wi++) {
            for (int hi = 0; hi < dm.height; hi++) {
                dm.pixelLast[wi][hi] = dm.img.getRaster().getSample(wi, hi, 0);

            }
        }

        String temp = Integer.toBinaryString(x);
        while (temp.length() != 8) {
            temp = "0" + temp;
        }

        char[] znaki = new char[temp.length()];
        int[] rules = new int[temp.length()];


        temp.getChars(0, temp.length(), znaki, 0);

        for (int a = 0; a < temp.length(); a++) {
            rules[a] = Character.getNumericValue(znaki[a]);
            if (rules[a] == 1) {rules[a] = 0;}
            else { rules[a] = 255;}
        }


        int i = 1;

        int[] pixelRow = new int[dm.width];


        for (int hi = 0; hi <= dm.height; hi++) {

            if (i == dm.height) {i=1;hi=1; }

            for (int j = 0; j < dm.width; j++) {
                pixelRow[j] = dm.pixelLast[j][hi];
            }

            for (int z = 0; z < pixelRow.length; z++) {

                int sasiadLewy = 0, sasiadPrawy = 0;

                if (z!=0 && z!= pixelRow.length-1)
                {
                    sasiadLewy = pixelRow[z - 1];
                    sasiadPrawy = pixelRow[z + 1];
                }

                else if(z==0){sasiadLewy=0; sasiadPrawy=pixelRow[z+1];}
                else if(z== pixelRow.length-1){sasiadLewy=pixelRow[z-1];sasiadPrawy=0;}


                if (sasiadLewy == 0 && pixelRow[z] == 0 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[0]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 0 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[1]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 255 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[2]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 255 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[3]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 0 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[4]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 0 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[5]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 255 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[6]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 255 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[7]);
                }
            }


            for (int wi = 0; wi < dm.width; wi++) {
                dm.pixelLast[wi][i] = dm.img.getRaster().getSample(wi, i, 0);
            }

            i++;
        }
    }

    void animateRuleNeighborsAreWhite(int x) {

        for (int wi = 0; wi < dm.width; wi++) {
            for (int hi = 0; hi < dm.height; hi++) {
                dm.pixelLast[wi][hi] = dm.img.getRaster().getSample(wi, hi, 0);

            }
        }

        String temp = Integer.toBinaryString(x);
        while (temp.length() != 8) {
            temp = "0" + temp;
        }

        char[] znaki = new char[temp.length()];
        int[] rules = new int[temp.length()];


        temp.getChars(0, temp.length(), znaki, 0);

        for (int a = 0; a < temp.length(); a++) {
            rules[a] = Character.getNumericValue(znaki[a]);
            if (rules[a] == 1) {rules[a] = 0;}
            else { rules[a] = 255;}
        }


        int i = 1;

        int[] pixelRow = new int[dm.width];


        for (int hi = 0; hi <= dm.height; hi++) {

            if (i == dm.height) {i=1;hi=1; }

            for (int j = 0; j < dm.width; j++) {
                pixelRow[j] = dm.pixelLast[j][hi];
            }

            for (int z = 0; z < pixelRow.length; z++) {

                int sasiadLewy = 0, sasiadPrawy = 0;

                if (z!=0 && z!= pixelRow.length-1)
                {
                    sasiadLewy = pixelRow[z - 1];
                    sasiadPrawy = pixelRow[z + 1];
                }

                else if(z==0){sasiadLewy=255; sasiadPrawy=pixelRow[z+1];}
                else if(z== pixelRow.length-1){sasiadLewy=pixelRow[z-1];sasiadPrawy=255;}


                if (sasiadLewy == 0 && pixelRow[z] == 0 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[0]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 0 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[1]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 255 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[2]);
                } else if (sasiadLewy == 0 && pixelRow[z] == 255 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[3]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 0 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[4]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 0 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[5]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 255 && sasiadPrawy == 0) {
                    dm.img.getRaster().setSample(z, i, 0, rules[6]);
                } else if (sasiadLewy == 255 && pixelRow[z] == 255 && sasiadPrawy == 255) {
                    dm.img.getRaster().setSample(z, i, 0, rules[7]);
                }
            }


            for (int wi = 0; wi < dm.width; wi++) {
                dm.pixelLast[wi][i] = dm.img.getRaster().getSample(wi, i, 0);
            }

            i++;
        }
    }

    void Initial(){
        for(int wi = 0; wi < dm.width; wi++) {
            for (int hi = 0; hi < dm.height; hi++) {
                if(hi==0&&wi==(dm.width/2)){dm.img.getRaster().setSample(wi,hi,0,0);}
               else{ dm.img.getRaster().setSample(wi,hi,0,255);}
            }
        }
    }

}