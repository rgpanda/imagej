//
// AbstractSwingDisplayPanel.java
//

/*
ImageJ software for multidimensional image processing and analysis.

Copyright (c) 2010, ImageJDev.org.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.
 * Neither the names of the ImageJDev.org developers nor the
names of its contributors may be used to endorse or promote products
derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
 */
package imagej.ui.swing.display;

import imagej.display.DisplayPanel;
import imagej.display.EventDispatcher;
import imagej.ui.common.awt.AWTDisplayWindow;

import imagej.ui.common.awt.AWTKeyEventDispatcher;

import imagej.ui.common.awt.AWTMouseEventDispatcher;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


/**
 * Swing implementation of display window.
 * 
 * @author Curtis Rueden
 * @author Grant Harris
 * @author Barry DeZonia
 */
public abstract class AbstractSwingDisplayPanel extends JPanel implements DisplayPanel {

	// -- DisplayWindow methods --

	@Override
	public void makeActive() {
		this.getParent().requestFocus();
	}


	@Override
	public void addEventDispatcher(final EventDispatcher dispatcher) {
		if(dispatcher instanceof AWTKeyEventDispatcher) {
			addKeyListener((AWTKeyEventDispatcher)dispatcher);
		}
		if(dispatcher instanceof AWTMouseEventDispatcher) {
			addMouseListener((AWTMouseEventDispatcher)dispatcher);
			addMouseMotionListener((AWTMouseEventDispatcher)dispatcher);
			addMouseWheelListener((AWTMouseEventDispatcher)dispatcher);
		}
	}
	

	@Override
	public void setTitle(String title) {
		Component parent = this.getParent();
		if (parent instanceof JInternalFrame) {
			((JInternalFrame) parent).setTitle(title);
		} else if (parent instanceof JFrame) {
			((JFrame) parent).setTitle(title);
		} else if (parent instanceof JTabbedPane) {
			// TODO: determine index of containing tab - TEST this.
			int tabIndex = ((JTabbedPane) parent).getSelectedIndex();
			((JTabbedPane) parent).setTitleAt(tabIndex, title);			
		} else {
			// cannot set title
		}
	}

}
