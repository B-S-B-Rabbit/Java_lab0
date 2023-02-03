# Implementation of matrix classes with support for complex numbers and basic operations on them
All methods and fields are documented according to the Javadoc standard.
It is possible to work both with matrices using
the real or complex type, and with the complex numbers themselves.

## Table of contents

0. [Main Features](#Main-features)
1. [Code Examples Complex Class](#Code-Examples-Complex-Class)
2. [Images](#Images)
3. [Дополнения](https://github.com/GnuriaN/format-README/blob/master/Дополнения.md)

## Main Features
____
- Complex class,supported operations
    - Create an object with certain values
    -  Create an object from another class object
    -  Perform chain operations of multiplication, addition, subtraction, division
    -  Create a new class object using the operations above
    -  Get formatted console output in algebraic and trigonometric form
    -  Find the module and the argument
____
- Matrix class,supported operations
    - Create an object with certain values
    -  Create an object from another class object
    -  Perform chain addition operations
    -  Create a new class object using addition, multiplication, and transpose operations
    -  Find the determinant
____
[:arrow_up:Оглавление](#Оглавление)

## Code Examples Complex Class
Constructors of the class of complex numbers `Complex`:
```Java
public class Complex {
    
    Complex(double real, double imag) {
        this.real_part = real;
        this.imag_part = imag;
    }
    Complex(double real)
    {
        this(real,0.0);
    }
    Complex()
    {
        this(0.0,0.0);
    }
    Complex(Complex other)
    {
        this(other.real_part, other.imag_part);
    }
}
```
____
Chained-the add method of the class `Complex`:
```Java
public class Complex {
    
    public Complex add(Complex other)
    {
        this.real_part += other.real_part;
        this.imag_part += other.imag_part;
        return this;
    }
}
```
____
Methods for finding the module and class argument `Complex`:
```Java
public class Complex {
    
    public double mod()
    {
        return Math.sqrt(real_part*real_part + imag_part*imag_part);
    }
    public double arg()
    {
        return Math.atan(imag_part/real_part);
    }
}
```
____
Methods of formatted class output `Complex`:
```Java
public class Complex {
    
    public String toString()
    {
        if (imag_part >=0)
        {
            return String.format("(%.4f + %.4fi) ",real_part,imag_part);
        }
        else
        {
            return String.format("(%.4f - %.4fi) ",real_part,Math.abs(imag_part));
        }
    }
    
    public String inTrigonometric()
    {
        return String.format("In trigonometric = %.4f * (cos(%.4f) + i*sin(%.4f))",
                mod(),arg(), arg());
    }
}
```
____
[:arrow_up:Оглавление](#Оглавление)
____

## Images