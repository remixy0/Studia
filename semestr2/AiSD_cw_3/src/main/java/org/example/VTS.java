package org.example;

import java.util.Stack;

public class VTS<E> extends Stack<E>{

    int position = 0;

    public VTS(){
        super();
        top();
    }

    public void down(){
        if(position == 0){
            System.out.println("Osiagnieto dol");
            return;
        }
        position--;
    }


    public void top(){
        position = super.size() - 1;
    }

    public E pop(){
        E element = super.pop();
        top();
        return element;
    }



}
