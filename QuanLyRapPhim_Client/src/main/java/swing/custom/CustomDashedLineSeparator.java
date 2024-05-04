package swing.custom;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class CustomDashedLineSeparator extends DottedLineSeparator {
    protected float dash = 5;
    protected float phase = 2.5f;
 
    public float getDash() {
        return dash;
    }
 
    public float getPhase() {
        return phase;
    }
 
    public void setDash(float dash) {
        this.dash = dash;
    }
 
    public void setPhase(float phase) {
        this.phase = phase;
    }
 
    public void draw(PdfContentByte canvas,
        float llx, float lly, float urx, float ury, float y) {
        canvas.saveState();
        canvas.setLineWidth(lineWidth);
        drawLine(canvas, llx, urx, y);
        canvas.restoreState();
    }
}