package xyz.yuelai;

public abstract class SubView extends FxView {

    private FxView parentView;

    public FxView getParentView() {
        return parentView;
    }

    public void setParentView(FxView parentView) {
        this.parentView = parentView;
    }
}
