package Util;

import Session.GuiDComponent;
import UI.GuiDUIPropertiesPanel;

public interface iGuiDPropMessenger
{
	public void changePropPanel(GuiDComponent gcmp);
	public void updateCompSel(String s);
}