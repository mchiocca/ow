package net.vtst.ow.eclipse.js.closure.properties;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import net.vtst.eclipse.easy.ui.properties.editors.AbstractFieldEditor;
import net.vtst.eclipse.easy.ui.properties.editors.DefaultCompoundEditor;
import net.vtst.eclipse.easy.ui.properties.editors.IEditorChangeEvent;
import net.vtst.eclipse.easy.ui.properties.editors.IEditorContainer;
import net.vtst.eclipse.easy.ui.util.SWTFactory;

public class ClosurePropertyEditor extends DefaultCompoundEditor {
  
  ClosureProjectPropertyRecord record = new ClosureProjectPropertyRecord();
  
  public ClosurePropertyEditor(IEditorContainer container) {
    super(container, 1);
    Group group1 = SWTFactory.createGroup(
        getComposite(), getMessage("closureBaseGroup"), 3, 1, GridData.FILL_HORIZONTAL);
    addControl(group1);
    record.useDefaultClosureBasePath.bindEditor(this, group1);
    record.closureBasePath.bindEditor(this, group1);
    Group group2 = SWTFactory.createGroup(
        getComposite(), getMessage("otherLibrariesGroup"), 3, 1, GridData.FILL_BOTH);
    addControl(group2);
    record.otherLibraries.bindEditor(this, group2);
    addControl(SWTFactory.createLabel(group2, getMessage("otherLibrariesHelp"), 3));
  }
  
  public void editorChanged(IEditorChangeEvent event) {
    triggerChangeEvent(event);
    record.closureBasePath.editor().setEnabled(!record.useDefaultClosureBasePath.editor().getCurrentValue());
  }
  
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    if (record.useDefaultClosureBasePath.editor().getCurrentValue())
      record.closureBasePath.editor().setEnabled(false); 
  }

}
