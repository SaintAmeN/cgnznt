package com.sshpr.cgnznt.demo.model;

import lombok.Getter;

@Getter
public enum LanguageChoice {
    C_SHARP(1, "C#"),
    VB_NET(2, "VB.NET"),
    F_SHARP(3, "F#"),
    Java(4, "Java"),
    Python(5, "Python"),
    C(6, "C (gcc)"),
    CPP(7, "C++ (gcc)"),
    Php(8, "Php"),
    Pascal(9, "Pascal"),
    Objective_C(10, "Objective-C"),
    Haskell(11, "Haskell"),
    Ruby(12, "Ruby"),
    Perl(13, "Perl"),
    Lua(14, "Lua"),
    Nasm(15, "Nasm"),
    Sql_Server(16, "Sql Server"),
    Javascript(17, "Javascript"),
    Lisp(18, "Lisp"),
    Prolog(19, "Prolog"),
    Go(20, "Go"),
    Scala(21, "Scala"),
    Scheme(22, "Scheme"),
    Node_js(23, "Node.js"),
    Python_3(24, "Python 3"),
    Octave(25, "Octave"),
    C_clang(26, "C (clang)"),
    Cpp_clang(27, "C++ (clang)"),
    Cpp_vcpp(28, "C++ (vc++)"),
    C_vc(29, "C (vc)"),
    D(30, "D"),
    R(31, "R"),
    Tcl(32, "Tcl"),
    MySQL(33, "MySQL"),
    PostgreSQL(34, "PostgreSQL"),
    Oracle(35, "Oracle"),
    Swift(37, "Swift"),
    Bash(38, "Bash"),
    Ada(39, "Ada"),
    Erlang(40, "Erlang"),
    Elixir(41, "Elixir"),
    Ocaml(42, "Ocaml"),
    Kotlin(43, "Kotlin"),
    Brainf(44, "Brainf***"),
    Fortran(45, "Fortran "),
    Rust(46, "Rust "),
    Clojure(47, "Clojure");

    private final int value;
    private final String name;

    LanguageChoice(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
