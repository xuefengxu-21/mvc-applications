/**
 * 
 */
package mvc;

public interface AppFactory {
    public Model makeModel();
    public View makeView(Model model);
    public String getTitle();
    public String[] getHelp();
    public String about();
    // public String getHelp();
    public String[] getEditCommands();
    public Command makeEditCommand(Model model, String type);
}
