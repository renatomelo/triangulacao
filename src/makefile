JC = javac

# Diretórios...
BIN=../bin

JFLAGS = -g -d $(BASE)$(BIN)

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Ponto.java \
	Triangulo.java \
	Poligono.java \
	ListaCircular.java \
	PoligonoEmListaCircular.java \
	triangulate.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) $(BASE)$(BIN)/*.class
