package com.ngobatai_lab6;


public class ViDu {
    public static void main(String[] args){
        System.out.println("--- Decorator Pattern ---");
        Component iconWindow = new IconWindow(new ScrollbarWindow(new Window()));
        iconWindow.draw();
    }
}

interface Component {
    void draw();
}

class Window implements Component {
    @Override public void draw(){
        System.out.println("Draw window");
    }
}

class Decorator implements Component {
    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override public void draw(){
        component.draw();
    }
}

class ScrollbarWindow extends Decorator {
    private String scrollbar = "scrollbar";

    public ScrollbarWindow(Component component){
        super(component);
    }
    
    @Override public void draw(){
        super.draw();
        System.out.println("Draw" + scrollbar);
    }
}

class IconWindow extends Decorator {
    private String icon = "icon";
    public IconWindow(Component component){
        super(component);
    }
    @Override public void draw(){
        super.draw();
        System.out.println("Draw" + icon);
    }
}


