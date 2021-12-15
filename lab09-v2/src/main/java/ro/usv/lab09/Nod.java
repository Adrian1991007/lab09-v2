package ro.usv.lab09;

class Nod<E> {
    E info;
    Nod<E> stg;
    Nod<E> dr;

    Nod(E info) {
        this.info = info;
        stg = null;
        dr = null;
    }

    private boolean esteFrunza() {
        return stg == null && dr == null;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}
