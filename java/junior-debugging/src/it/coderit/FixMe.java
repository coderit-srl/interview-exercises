package it.coderit;

public class FixMe {

    public static void main(String[] args) {

        Box box1 = new Box();
        box1.setDepth(1);
        box1.setWidth(1);
        box1.setHeight(1);

        Box box2 = new Box();
        box2.setDepth(2);
        box2.setWidth(2);
        box2.setHeight(2);

        Box box3 = new Box();
        box2.setDepth(1);
        box2.setWidth(1);
        box2.setHeight(2);

        Box box4 = new Box();
        box4.setDepth(2);
        box4.setWidth(1);
        box4.setHeight(2);

        Box[] boxes = {box1, box2, box3, box4};
        int sum = 0;
        for (int i = 0; i <= boxes.length; i++) {
            sum += boxes[i].volume();
        }
        System.out.println("Total volume is: " + sum);
    }

    static class Box {
        private int h;
        private int w;
        private int d;

        public void setHeight(int h) {
            this.h = h;
        }

        public void setWidth(int w) {
            this.w = w;
        }

        public void setDepth(int d) {
            this.w = d;
        }

        public int volume() {
            return h*w*w;
        }
    }
}

