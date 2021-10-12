package alivestill.utils.Triplet;

import java.util.Objects;

public class Triplet<U, V, W> {
    U u;
    V v;
    W w;

    Triplet(U u, V v, W w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) o;
        return u.equals(triplet.u) && v.equals(triplet.v) && w.equals(triplet.w);
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, w);
    }

    public U getU() {
        return u;
    }

    public void setU(U u) {
        this.u = u;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

    public W getW() {
        return w;
    }

    public void setW(W w) {
        this.w = w;
    }
}
