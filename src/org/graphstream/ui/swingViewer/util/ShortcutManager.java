/*
 * Copyright 2006 - 2012
 *      Stefan Balev       <stefan.balev@graphstream-project.org>
 *      Julien Baudry	<julien.baudry@graphstream-project.org>
 *      Antoine Dutot	<antoine.dutot@graphstream-project.org>
 *      Yoann Pigné	<yoann.pigne@graphstream-project.org>
 *      Guilhelm Savin	<guilhelm.savin@graphstream-project.org>
 *  
 * GraphStream is a library whose purpose is to handle static or dynamic
 * graph, create them from scratch, file or any source and display them.
 * 
 * This program is free software distributed under the terms of two licenses, the
 * CeCILL-C license that fits European law, and the GNU Lesser General Public
 * License. You can  use, modify and/ or redistribute the software under the terms
 * of the CeCILL-C license as circulated by CEA, CNRS and INRIA at the following
 * URL <http://www.cecill.info> or under the terms of the GNU LGPL as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C and LGPL licenses and that you accept their terms.
 */
package org.graphstream.ui.swingViewer.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.swingViewer.View;

/**
 * Utility to centralise the shortcuts and actions for all view instances.
 */
public class ShortcutManager implements KeyListener {
	// Attributes

	/**
	 * The viewer to control.
	 */
	protected View view;

	protected double viewPercent = 1;

	protected Point3 viewPos = new Point3();

	protected double rotation = 0;

	// Construction

	/**
	 * New manager operating on the given viewer.
	 * 
	 * @param view
	 *            The graph view to control.
	 */
	public ShortcutManager(View view) {
		this.view = view;
	}

	// Events

	/**
	 * A key has been pressed.
	 * 
	 * @param event
	 *            The event that generated the key.
	 */
	public void keyPressed(KeyEvent event) {
		Camera camera = view.getCamera();
		
		if (event.getKeyCode() == KeyEvent.VK_PAGE_UP) {
			camera.setViewPercent(camera.getViewPercent() - 0.05f);
		} else if (event.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			camera.setViewPercent(camera.getViewPercent() + 0.05f);
		} else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
			if ((event.getModifiers() & KeyEvent.ALT_MASK) != 0) {
				double r = camera.getViewRotation();
				camera.setViewRotation(r - 5);
			} else {
				double delta = 0;

				if ((event.getModifiers() & KeyEvent.SHIFT_MASK) != 0)
					delta = camera.getGraphDimension() * 0.1f;
				else
					delta = camera.getGraphDimension() * 0.01f;

				Point3 p = camera.getViewCenter();
				camera.setViewCenter(p.x - delta, p.y, 0);
			}
		} else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
			if ((event.getModifiers() & KeyEvent.ALT_MASK) != 0) {
				double r = camera.getViewRotation();
				camera.setViewRotation(r + 5);
			} else {
				double delta = 0;

				if ((event.getModifiers() & KeyEvent.SHIFT_MASK) != 0)
					delta = camera.getGraphDimension() * 0.1f;
				else
					delta = camera.getGraphDimension() * 0.01f;

				Point3 p = camera.getViewCenter();
				camera.setViewCenter(p.x + delta, p.y, 0);
			}
		} else if (event.getKeyCode() == KeyEvent.VK_UP) {
			double delta = 0;

			if ((event.getModifiers() & KeyEvent.SHIFT_MASK) != 0)
				delta = camera.getGraphDimension() * 0.1f;
			else
				delta = camera.getGraphDimension() * 0.01f;

			Point3 p = camera.getViewCenter();
			camera.setViewCenter(p.x, p.y + delta, 0);
		} else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			double delta = 0;

			if ((event.getModifiers() & KeyEvent.SHIFT_MASK) != 0)
				delta = camera.getGraphDimension() * 0.1f;
			else
				delta = camera.getGraphDimension() * 0.01f;

			Point3 p = camera.getViewCenter();
			camera.setViewCenter(p.x, p.y - delta, 0);
		}
	}

	/**
	 * A key has been pressed.
	 * 
	 * @param event
	 *            The event that generated the key.
	 */
	public void keyReleased(KeyEvent event) {
	}

	/**
	 * A key has been typed.
	 * 
	 * @param event
	 *            The event that generated the key.
	 */
	public void keyTyped(KeyEvent event) {
		if (event.getKeyChar() == 'R') {
			view.getCamera().resetView();
		}
		// else if( event.getKeyChar() == 'B' )
		// {
		// view.setModeFPS( ! view.getModeFPS() );
		// }
	}
}