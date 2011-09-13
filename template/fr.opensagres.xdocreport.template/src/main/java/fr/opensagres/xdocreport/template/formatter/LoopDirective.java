package fr.opensagres.xdocreport.template.formatter;

public class LoopDirective extends Directive {

	private final String sequence;
	private final String item;

	public LoopDirective(String startLoopDirective, String endLoopDirective,
			String sequence, String item) {
		super(startLoopDirective, endLoopDirective);
		this.sequence = sequence;
		this.item = item;
	}

	public String getSequence() {
		return sequence;
	}

	public String getItem() {
		return item;
	}

	@Override
	public DirectiveType getType() {
		return DirectiveType.LOOP;
	}
}