package mvc;


public abstract class Model extends Bean{
    private Boolean unsavedChanges = false;
    private String fileName = null;

    public abstract String toString();

    public void changed(){
        unsavedChanges = true;
        firePropertyChange("color", 1, 2);
    }

    public void setUnsavedChanges(boolean saved){
        unsavedChanges = saved;
    }
    public boolean getUnsavedChanges(){
        return unsavedChanges;
    }

    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}
